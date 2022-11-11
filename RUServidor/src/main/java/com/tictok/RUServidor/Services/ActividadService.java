package com.tictok.RUServidor.Services;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Projections.ActividadInfo;
import com.tictok.RUServidor.Projections.CuentaCheckIns;
import com.tictok.RUServidor.Projections.CuentaReservas;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Exceptions.CuentaNoExisteException;
import com.tictok.RUServidor.Exceptions.CuposAgotadosException;
import com.tictok.RUServidor.Exceptions.EntidadNoExisteException;
import com.tictok.RUServidor.Mappers.ActividadMapper;
import com.tictok.RUServidor.Mappers.HorarioMapper;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ActividadService {

    private final UsuarioRepository usuarioRepository;
    private final ActividadRepository actividadRepository;
    private final ReservaActividadRepository reservaActividadRepository;
    private final CentroService centroService;
    private final CuentaService cuentaService;
    private final ImagenRepository imagenRepository;
    private final UsuarioService usuarioService;
    private final CheckInActividadRepository checkInActividadRepository;

    @Autowired
    public ActividadService(UsuarioRepository usuarioRepository, ActividadRepository actividadRepository, ReservaActividadRepository reservaActividadRepository, ImagenRepository imagenRepository, CentroService centroService, CuentaService cuentaService, UsuarioService usuarioService, CheckInActividadRepository checkInActividadRepository) throws IOException {
        this.usuarioRepository = usuarioRepository;
        this.actividadRepository = actividadRepository;
        this.reservaActividadRepository = reservaActividadRepository;
        this.imagenRepository = imagenRepository;
        this.centroService = centroService;
        this.usuarioService = usuarioService;
        this.checkInActividadRepository = checkInActividadRepository;

       // agregarImagenPrueba();
        this.cuentaService = cuentaService;
    }

    private void agregarImagenPrueba() throws IOException {
        Imagen imagen = new Imagen();
        FileInputStream fis = new FileInputStream("C:/Users/agustin/Downloads/imagenTIC2.txt");
        String laImagen = IOUtils.toString(fis, "UTF-8");
        imagen.setImagenString(laImagen);
        imagenRepository.save(imagen);
    }

    @Transactional
    public ReservaDTO reservarActividad(ReservaDTO reservaDTO) throws CuposAgotadosException, CuentaNoExisteException {
        Usuario usuario = cuentaService.findOnebyId(reservaDTO.getMailUsuario()).getUsuario();
        Horario horarioId = HorarioMapper.fromHorarioDTOToHorario(reservaDTO.getHorario());
        ServicioId actividadId = new ServicioId(reservaDTO.getNombreActividad(), reservaDTO.getNombreCentro(),
                horarioId.getDia(), horarioId.getHoraInicio(), horarioId.getHoraFin());
        Actividad actividad = actividadRepository.getReferenceById(actividadId);

        LocalDate fecha = HorarioMapper.getFecha(horarioId.getDia());
        Date dateFecha = Date.valueOf(fecha);

        if (actividad.getCupos()!= -1){
            List<CuentaReservas> cuentaReservasList = reservaActividadRepository.countReservasByServicioIdAndFecha(actividadId, dateFecha);
            if (!cuentaReservasList.isEmpty()){
                int cuposReservados = (int) cuentaReservasList.get(0).getCupos();
                if (actividad.getCupos() <= cuposReservados){
                    throw new CuposAgotadosException();
                }
            }
        }

        ReservaActividad reservaActividad = new ReservaActividad(usuario, dateFecha, actividad);
        reservaActividad = reservaActividadRepository.save(reservaActividad);

        return ReservaMapper.fromReservaActividadToReserva2DTO(reservaActividad);
    }

    public void checkInActividadSinReserva(CheckInDTO checkInDTO) throws CuposAgotadosException, CuentaNoExisteException, UsuarioNoExisteException {
        System.out.println("11111111111111111111111111111111111111111");
        Usuario usuario = usuarioService.findOnebyId2(checkInDTO.getCedulaUsuario());
        System.out.println(usuario.getAddress());
        System.out.println("22222222222222222222222222222222222222222");
        Horario horarioId = HorarioMapper.fromHorarioDTOToHorario(checkInDTO.getHorario());

        ServicioId actividadId = new ServicioId(checkInDTO.getNombreActividad(), checkInDTO.getNombreCentro(), horarioId.getDia(), horarioId.getHoraInicio(), horarioId.getHoraFin());
        Actividad actividad = actividadRepository.getReferenceById(actividadId);
        System.out.println("3333333333333333333333333333333333333333");

        LocalDate fecha = HorarioMapper.getFecha(horarioId.getDia());
        Date dateFecha = Date.valueOf(fecha);

        System.out.println(actividad.getCupos() + "---------------------------------------------------cupos");
        if (actividad.getCupos()!= -1){
            List<CuentaCheckIns> cuentaCheckInsList = checkInActividadRepository.countCheckInsByServicioIdAndFecha(actividadId,dateFecha);
            List<CuentaReservas> cuentaReservasList = reservaActividadRepository.countReservasByServicioIdAndFecha(actividadId, dateFecha);
            int cuposReservados = 0;
            int cuposCheckineados = 0;
            if (!cuentaReservasList.isEmpty()) {
                cuposReservados = (int) cuentaReservasList.get(0).getCupos();
            }
            if (!cuentaCheckInsList.isEmpty()) {
                cuposCheckineados = (int) cuentaCheckInsList.get(0).getCupos();
            }
            int cuposUsados = cuposReservados + cuposCheckineados;
                //int cuposYaUsadosPeroNoReservados = (int) cu
                if (actividad.getCupos() <= cuposUsados){
                    throw new CuposAgotadosException();
                }
            }
        CheckInActividad checkInActividad = new CheckInActividad(usuario, dateFecha, actividad);
        checkInActividad = checkInActividadRepository.save(checkInActividad);
        //return  CheckInMapper.fromCheckInActividadToCheckInDTO(checkInActividad); ????????
    }

    @Transactional
    public void checkInActividadConReserva(Long codigoReserva) throws EntidadNoExisteException {
         Optional<ReservaActividad> reservaActividadOptional = reservaActividadRepository.findById(codigoReserva);
         if (reservaActividadOptional.isEmpty()) {
             throw new EntidadNoExisteException("La reserva no existe");
         }
         ReservaActividad reservaActividad = reservaActividadOptional.get();
         Usuario usuario = reservaActividad.getUsuario();
         Date dateFecha = reservaActividad.getFecha();
         Actividad actividad = reservaActividad.getActividad();
         CheckInActividad checkInActividad = new CheckInActividad(usuario, dateFecha, actividad);
         reservaActividadRepository.delete(reservaActividad);
    }

    public List<SuperActividadDTO> findAll(){
        List<Actividad> actividadList = actividadRepository.findAll();
        if (actividadList.isEmpty()){
            return null;
        }
        List<SuperActividadDTO> listaSuperActividadDTO = ActividadMapper.fromActividadesListToSuperActividadDTOList(actividadList);
        return listaSuperActividadDTO;
    }

    @Transactional
    public ListaDTOConCount findAllPageable(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Tuple> actividadInfosObjects = actividadRepository.findDistinctBy(paging);
        int pages = actividadInfosObjects.getTotalPages();
        if (actividadInfosObjects.isEmpty()){
            return null;
        }
        List<SuperActividadDTO> superActividadDTOList =
                ActividadMapper.fromQueryResultListToSuperActividadDTOList(actividadInfosObjects.getContent(), imagenRepository);
        ListaDTOConCount listaDTOConCount = new ListaDTOConCount(pages, superActividadDTOList);
        return listaDTOConCount;
    }

    private void setImagenSuperActividad(SuperActividadDTO superActividad){
        actividadRepository.findByCentroAndNombre(superActividad.getNombreCentro(), superActividad.getNombreServicio());
    }

    public void guardarActividad(NuevoServicioDTO nuevaActividadDTO, String mailCentro) throws CuentaNoExisteException {
        CentroDeportivo centro1 = cuentaService.findOnebyId(mailCentro).getCentroDeportivo();
        Imagen imagen = null;
        if (nuevaActividadDTO.getImageString() != null) {
            imagen = new Imagen(nuevaActividadDTO.getImageString());
            imagenRepository.save(imagen);
        }
        if (nuevaActividadDTO.getHorarios() != null && !nuevaActividadDTO.getPaseLibre()) {
            for (int i = 0; i < nuevaActividadDTO.getHorarios().size(); i++) {
                HorarioDTO horarioDTOi = nuevaActividadDTO.getHorarios().get(i);

                Integer horaInicio = horarioDTOi.getHoraInicio();
                Integer horaFin = horarioDTOi.getHoraFin();

                LocalTime horaInicio1 = LocalTime.of(horaInicio / 100, horaInicio - (horaInicio / 100) * 100);
                LocalTime horaFin1 = LocalTime.of(horaFin / 100, horaFin - (horaFin / 100) * 100);
                DayOfWeek dia = HorarioMapper.setearDia(horarioDTOi.getDia());

                Actividad actividadI = new Actividad(centro1, nuevaActividadDTO.getNombreServicio(), dia, horaInicio1, horaFin1, nuevaActividadDTO.getPrecio(), nuevaActividadDTO.getCupos(), nuevaActividadDTO.getPaseLibre());
                if (nuevaActividadDTO.getImageString() != null) {
                    actividadI.setImagen(imagen);
                }
                actividadRepository.save(actividadI);
                centro1.setActividad(actividadI);
            }
        } else {
            Actividad actividadI = new Actividad(centro1, nuevaActividadDTO.getNombreServicio(), null, null, null, nuevaActividadDTO.getPrecio(), nuevaActividadDTO.getCupos(), nuevaActividadDTO.getPaseLibre());
            if (nuevaActividadDTO.getImageString() != null) {
                actividadI.setImagen(imagen);
            }
            actividadRepository.save(actividadI);
            centro1.setActividad(actividadI);
        }
    }

    public List<SuperActividadDTO> buscarActividades(String campoBusqueda){
        List<Actividad> actividadList = actividadRepository.findByNombreOBarrioIsLike(campoBusqueda.toUpperCase());
        if (actividadList.isEmpty()){
            return null;
        }
        List<SuperActividadDTO> listaSuperActividadDTO = ActividadMapper.fromActividadesListToSuperActividadDTOList(actividadList);
        return listaSuperActividadDTO;
    }
    @Transactional
    public ActividadConHorariosYCuposDTO getActividadConHorariosYCuposDTO(String centroDeportivo, String actividadNombre) throws EntidadNoExisteException {
        System.out.println(centroDeportivo + "   " + actividadNombre);
        List<Actividad> listaDeActividades = actividadRepository.findByCentroAndNombre(centroDeportivo, actividadNombre);
        if (listaDeActividades.isEmpty()) {
            throw new EntidadNoExisteException("La actividad de ese centro y ese nombre no existe");
        }
        Actividad actividadPadre = listaDeActividades.get(0);

        Double precio = actividadPadre.getPrecio();
        CentroDeportivo centro = actividadPadre.getCentroDeportivo();
        String address = centro.getAddress();
        String barrio = centro.getBarrio();
        String telefono = centro.getTelefono();

        ActividadConHorariosYCuposDTO actividadConHorariosYCuposDTO =
                new ActividadConHorariosYCuposDTO(actividadNombre, centroDeportivo,
                precio, address, barrio, telefono, new ArrayList<HorarioConCuposDTO>());

        Date fechaHoy = Date.valueOf(LocalDate.now());
        Date fechaFin = Date.valueOf(LocalDate.now().plusDays(6));
        Actividad actividad;
        int dia;
        int horaInicio;
        int horaFin;
        CuentaReservas cuentaReservas;
        int cuposLibres;

        List<CuentaReservas> actividadesReservadas = reservaActividadRepository.conseguirHorariosReservadosEntreFechas(actividadNombre, centroDeportivo, fechaHoy, fechaFin);


        for (int i = 0; i < listaDeActividades.size(); i++) {
            actividad = listaDeActividades.get(i);
            dia = HorarioMapper.getDia(actividad.getActividadId().getDia());
            horaInicio = HorarioMapper.fromLocalTimeToIntHora(actividad.getActividadId().getHoraInicio());
            horaFin = HorarioMapper.fromLocalTimeToIntHora(actividad.getActividadId().getHoraFin());
            cuposLibres = actividadPadre.getCupos();
            for (int j = 0; j < actividadesReservadas.size(); j++) {
                cuentaReservas = actividadesReservadas.get(j);
                if (actividad.getActividadId().equals(cuentaReservas.getServicioId())) {
                    cuposLibres = cuposLibres - (int) cuentaReservas.getCupos();
                }
            }
            actividadConHorariosYCuposDTO.addHorarioConCupos(new HorarioConCuposDTO(dia, horaInicio, horaFin, cuposLibres));
        }
        return actividadConHorariosYCuposDTO;
    }
}
