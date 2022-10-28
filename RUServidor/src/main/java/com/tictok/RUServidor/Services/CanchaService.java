package com.tictok.RUServidor.Services;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Entities.NotTables.CuentaReservas;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Mappers.ActividadMapper;
import com.tictok.RUServidor.Mappers.CanchaMapper;
import com.tictok.RUServidor.Mappers.HorarioMapper;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import com.tictok.RUServidor.Repositories.ReservaCanchaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public CanchaService(UsuarioRepository usuarioRepository, CanchaRepository canchaRepository, ReservaCanchaRepository reservaCanchaRepository, CuentaService cuentaService) {
        this.usuarioRepository = usuarioRepository;
        this.canchaRepository = canchaRepository;
        this.reservaCanchaRepository = reservaCanchaRepository;
        this.cuentaService = cuentaService;
    }

    public ReservaDTO reservarCancha(Reserva2DTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException, ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuentaNoExisteException {
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

    private ReservaCancha reservarCanchaPadre(Reserva2DTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException, CuentaNoExisteException {
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

    private ReservaCancha reservarCanchaHijo(Reserva2DTO reservaDTO) throws ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuentaNoExisteException {
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

    public List<SuperCanchaDTO> buscarCanchas(String campoBusqueda){
        List<Cancha> canchaList = canchaRepository.findByNombreOBarrioIsLike(campoBusqueda.toUpperCase());
        if (canchaList.isEmpty()){
            return null;
        }
        List<SuperCanchaDTO> listaSuperCanchaDTO = CanchaMapper.fromCanchaListToSuperCanchaDTOList(canchaList);
        return listaSuperCanchaDTO;
    }

    public CanchaConHorariosYCuposDTO getCanchaConHorariosYCuposDTO(String centroDeportivo, String canchaNombre) throws EntidadNoExisteException {
        List<Cancha> listaDeCanchas = canchaRepository.findByCentroAndNombre(centroDeportivo, canchaNombre);
        if (listaDeCanchas.isEmpty()) {
            throw new EntidadNoExisteException("La cancha de ese centro y ese nombre no existe");
        }
        Cancha canchaPadre = listaDeCanchas.get(0);

        Integer precio = canchaPadre.getPrecio();
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
}
