package com.tictok.RUServidor.Services;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Mappers.*;
import com.tictok.RUServidor.Projections.CuentaReservas;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class CanchaService {

    private final CuentaService cuentaService;
    private final UsuarioRepository usuarioRepository;
    private final CanchaRepository canchaRepository;
    private final ReservaCanchaRepository reservaCanchaRepository;
    private final ImagenRepository imagenRepository;
    private final CheckInCanchaRepository checkInCanchaRepository;

    @Autowired
    public CanchaService(UsuarioRepository usuarioRepository, CanchaRepository canchaRepository, ReservaCanchaRepository reservaCanchaRepository, CuentaService cuentaService, ImagenRepository imagenRepository,CheckInCanchaRepository checkInCanchaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.canchaRepository = canchaRepository;
        this.reservaCanchaRepository = reservaCanchaRepository;
        this.cuentaService = cuentaService;
        this.imagenRepository = imagenRepository;
        this.checkInCanchaRepository = checkInCanchaRepository;
    }

    public ReservaDTO reservarCancha(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException, ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuentaNoExisteException {
        ReservaCancha reservaCancha;
        ReservaDTO reservaDTOADevolver = null;

        if (reservaDTO.getCodigoReservaPadre() == null){
            reservaCancha = reservarCanchaPadre(reservaDTO);
            reservaDTOADevolver = ReservaMapper.fromReservaCanchaToReservaDTO(reservaCancha);
            reservaDTOADevolver.setCodigoReservaPadre(reservaDTOADevolver.getCodigoReserva());
        }
        else {
            reservaCancha = reservarCanchaHijo(reservaDTO);
            reservaDTOADevolver = ReservaMapper.fromReservaCanchaToReservaDTO(reservaCancha);
            reservaDTOADevolver.setCodigoReservaPadre(reservaDTO.getCodigoReservaPadre());
        }
        return reservaDTOADevolver;
    }

    private ReservaCancha reservarCanchaPadre(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException, CuentaNoExisteException {
        Usuario usuario = cuentaService.findOnebyId(reservaDTO.getMailUsuario()).getUsuario();
        //Usuario usuario = usuarioRepository.findById(reservaDTO.getCedulaUsuario()).get();
        Horario horarioId = HorarioMapper.fromHorarioDTOToHorario(reservaDTO.getHorario());
        ServicioId canchaId = new ServicioId(reservaDTO.getNombreActividad(), reservaDTO.getNombreCentro(),
                horarioId.getDia(), horarioId.getHoraInicio(), horarioId.getHoraFin());
        Cancha cancha = canchaRepository.getReferenceById(canchaId);
        LocalDate fecha = HorarioMapper.getFecha(horarioId.getDia());
        Date dateFecha = Date.valueOf(fecha);

        LocalDateTime fechaConHora = LocalDateTime.of(fecha, horarioId.getHoraInicio());
        if (reservaCanchaRepository.existsReservaConCanchaHoraYFecha(dateFecha, horarioId.getHoraInicio(),
                reservaDTO.getNombreCentro(), reservaDTO.getNombreActividad())){
            throw new CanchaYaReservadaException();
        }
        if (fechaConHora.isBefore(LocalDateTime.now())){
            throw new ReservaPosteriorAlInicioException();
        }

        ReservaCancha reservaCancha = new ReservaCancha(usuario, cancha, dateFecha);
        return reservaCanchaRepository.save(reservaCancha);
    }

    public void checkInCancha(CheckInDTO checkInCancha) throws ReservaNoExisteException {
        String tipo = checkInCancha.getTipo();
        Long codReserva = checkInCancha.getCodigoCheckIn(); //viaja en CodigoCheckIn pero es el de la reserva

        Optional<ReservaCancha> reserva= reservaCanchaRepository.findById(codReserva);  //get reserva de ESE usuario
        if (!reserva.isPresent()) {
            throw new ReservaNoExisteException();
        }
        ReservaCancha reservaCancha = reserva.get();
        Long codReservaPadre;
        boolean esPadre = false;
        try {
            codReservaPadre = reservaCancha.getReservaCanchaPadre().getId();
        }catch (NullPointerException n ){
            codReservaPadre = reservaCancha.getId();
            esPadre =true;
        }

        Usuario usuario = reservaCancha.getUsuario();
        Cancha cancha = reservaCancha.getCancha();
        Date date = reservaCancha.getFecha();
        Double precio = cancha.getPrecio();

        List<CheckInCancha> listCheckInsPrevios = checkInCanchaRepository.findByReservaCanchaPadre(codReservaPadre);
        if(listCheckInsPrevios.isEmpty()){
            CheckInCancha check;
            if(esPadre){
                check = new CheckInCancha(reservaCancha,date,usuario,cancha,precio);
            }else {
                check = new CheckInCancha(reservaCancha.getReservaCanchaPadre(),date,usuario,cancha,precio);
            }
            checkInCanchaRepository.save(check);
        }else{
            Double precioEntreCheckIns = precio/(listCheckInsPrevios.size()+1);
            for(int i = 0; i<listCheckInsPrevios.size();i++){
                CheckInCancha checkIni = listCheckInsPrevios.get(i);
                checkIni.setPrecio(precioEntreCheckIns);
                checkInCanchaRepository.save(checkIni);
            }
            CheckInCancha check;
            if(esPadre){
                check = new CheckInCancha(reservaCancha,date,usuario,cancha,precioEntreCheckIns);
            }else {
                check = new CheckInCancha(reservaCancha.getReservaCanchaPadre(),date,usuario,cancha,precioEntreCheckIns);
            }
            checkInCanchaRepository.save(check);
        }
    }

    private ReservaCancha reservarCanchaHijo(ReservaDTO reservaDTO) throws ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuentaNoExisteException {
        Usuario usuario = cuentaService.findOnebyId(reservaDTO.getMailUsuario()).getUsuario();
        Optional<ReservaCancha> reservaPadreO = reservaCanchaRepository.findById(reservaDTO.getCodigoReservaPadre());
        if (reservaPadreO.isEmpty()){
            throw new ReservaPadreNoExisteException();
        }
        ReservaCancha reservaPadre = reservaPadreO.get();
        Cancha cancha = reservaPadre.getCancha();
        Date dateFecha = reservaPadre.getFecha();

        LocalDateTime fechaConHora = LocalDateTime.of(dateFecha.toLocalDate(), reservaPadre.getCancha().getCanchaId().getHoraFin());
        if (fechaConHora.isBefore(LocalDateTime.now())) {
            throw new ReservaPosteriorAlFinException();
        }

        ReservaCancha reservaCancha = new ReservaCancha(usuario, cancha, dateFecha);
        reservaCancha.setReservaCanchaPadre(reservaPadre);
        return reservaCanchaRepository.save(reservaCancha);
    }

    public List<SuperCanchaDTO> findAll() {
        List<Cancha> canchaList = canchaRepository.findAll();
        if (canchaList.isEmpty()){
            return null;
        }
        List<SuperCanchaDTO> listaSuperCanchaDTO = CanchaMapper.fromCanchaListToSuperCanchaDTOList(canchaList);
        return listaSuperCanchaDTO;
    }

    public ListaCanchasDTOConCount procesarCanchas(Page<Tuple> canchasInfosObjects){
        int pages = canchasInfosObjects.getTotalPages();
        if (canchasInfosObjects.isEmpty()){
            return null;
        }
        List<SuperCanchaDTO> superCanchaDTOList =
                CanchaMapper.fromQueryResultListToSuperCanchaDTOList(canchasInfosObjects.getContent(), imagenRepository);
        ListaCanchasDTOConCount listaCanchasDTOConCount = new ListaCanchasDTOConCount(pages, superCanchaDTOList);
        return listaCanchasDTOConCount;
    }
    public ListaCanchasDTOConCount findAllPageable(int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("precio"));
        Page<Tuple> canchasInfosObjects = canchaRepository.findDistinctBy(paging);
        return procesarCanchas(canchasInfosObjects);
    }

    public List<SuperCanchaDTO> buscarCanchas(String campoBusqueda){
        List<Cancha> canchaList = canchaRepository.findByNombreOBarrioIsLike(campoBusqueda.toUpperCase());
        if (canchaList.isEmpty()){
            return null;
        }
        List<SuperCanchaDTO> listaSuperCanchaDTO = CanchaMapper.fromCanchaListToSuperCanchaDTOList(canchaList);
        return listaSuperCanchaDTO;
    }

    @Transactional
    public CanchaConHorariosYCuposDTO getCanchaConHorariosYCuposDTO(String centroDeportivo, String canchaNombre) throws EntidadNoExisteException {
        List<Cancha> listaDeCanchas = canchaRepository.findByCentroAndNombre(centroDeportivo, canchaNombre);
        if (listaDeCanchas.isEmpty()) {
            throw new EntidadNoExisteException("La cancha de ese centro y ese nombre no existe");
        }
        Cancha canchaPadre = listaDeCanchas.get(0);

        Double precio = canchaPadre.getPrecio();
        CentroDeportivo centro = canchaPadre.getCentroDeportivo();
        String address = centro.getAddress();
        String barrio = centro.getBarrio();
        String telefono = centro.getTelefono();

        CanchaConHorariosYCuposDTO canchaConHorariosYCuposDTO = new CanchaConHorariosYCuposDTO(canchaNombre, centroDeportivo,
                precio, address, barrio, telefono, new ArrayList<HorarioConCuposDTO>());

        Date fechaHoy = Date.valueOf(LocalDate.now());
        Date fechaFin = Date.valueOf(LocalDate.now().plusDays(6));
        Cancha cancha;
        int dia;
        int horaInicio;
        int horaFin;
        CuentaReservas cuentaReservas;
        int cuposLibres;

        List<CuentaReservas> canchasReservadas = reservaCanchaRepository.conseguirHorariosReservadosEntreFechas(canchaNombre, centroDeportivo, fechaHoy, fechaFin);


        for (int i = 0; i < listaDeCanchas.size(); i++) {
            cancha = listaDeCanchas.get(i);
            dia = HorarioMapper.getDia(cancha.getCanchaId().getDia());
            horaInicio = HorarioMapper.fromLocalTimeToIntHora(cancha.getCanchaId().getHoraInicio());
            horaFin = HorarioMapper.fromLocalTimeToIntHora(cancha.getCanchaId().getHoraFin());
            cuposLibres = 1;
            for (int j = 0; j < canchasReservadas.size(); j++) {
                cuentaReservas = canchasReservadas.get(j);
                if (cancha.getCanchaId().equals(cuentaReservas.getServicioId())) {
                    cuposLibres = 0;
                }
            }
            canchaConHorariosYCuposDTO.addHorarioConCupos(new HorarioConCuposDTO(dia, horaInicio, horaFin, cuposLibres));
        }
        return canchaConHorariosYCuposDTO;
    }

    public void guardarCancha(NuevoServicioDTO nuevaCanchaDTO, String mailCentro) throws CuentaNoExisteException {
        CentroDeportivo centro1 = cuentaService.findOnebyId(mailCentro).getCentroDeportivo();
        Imagen imagen = null;
        if (nuevaCanchaDTO.getImageString() != null) {
            imagen = new Imagen(nuevaCanchaDTO.getImageString());
            imagenRepository.save(imagen);
        }
        for (int i = 0; i < nuevaCanchaDTO.getHorarios().size(); i++) {
            HorarioDTO horarioDTOi = nuevaCanchaDTO.getHorarios().get(i);

            Integer horaInicio = horarioDTOi.getHoraInicio();
            Integer horaFin = horarioDTOi.getHoraFin();

            LocalTime horaInicio1 = LocalTime.of(horaInicio / 100, horaInicio - (horaInicio / 100) * 100);
            LocalTime horaFin1 = LocalTime.of(horaFin / 100, horaFin - (horaFin / 100) * 100);
            DayOfWeek dia = HorarioMapper.setearDia(horarioDTOi.getDia());

            Cancha canchaI = new Cancha(centro1, nuevaCanchaDTO.getNombreServicio(), dia, horaInicio1, horaFin1, nuevaCanchaDTO.getPrecio(), nuevaCanchaDTO.getCupos());
            if (nuevaCanchaDTO.getImageString() != null) {
                canchaI.setImagen(imagen);
            }
            canchaRepository.save(canchaI);
            centro1.setCancha(canchaI);
        }
    }
}
