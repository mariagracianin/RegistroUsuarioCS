package com.tictok.RUServidor.Services;

import com.tictok.Commons.ReservaDTO;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.ReservaCancha;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Mappers.HorarioMapper;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import com.tictok.RUServidor.Repositories.ReservaCanchaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CanchaService {


    private final UsuarioRepository usuarioRepository;
    private final CanchaRepository canchaRepository;
    private final ReservaCanchaRepository reservaCanchaRepository;

    @Autowired
    public CanchaService(UsuarioRepository usuarioRepository, CanchaRepository canchaRepository, ReservaCanchaRepository reservaCanchaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.canchaRepository = canchaRepository;
        this.reservaCanchaRepository = reservaCanchaRepository;
    }

    public ReservaDTO reservarCancha(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException, ReservaPadreNoExisteException, ReservaPosteriorAlFinException {
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

    private ReservaCancha reservarCanchaPadre(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException, CanchaYaReservadaException {

        Usuario usuario = usuarioRepository.findById(reservaDTO.getCedulaUsuario()).get();
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

        ReservaCancha reservaCancha = new ReservaCancha(usuario, usuario, cancha, dateFecha);
        return reservaCanchaRepository.save(reservaCancha);
    }

    private ReservaCancha reservarCanchaHijo(ReservaDTO reservaDTO) throws ReservaPadreNoExisteException, ReservaPosteriorAlFinException {
        Usuario usuario = usuarioRepository.findById(reservaDTO.getCedulaUsuario()).get();
        Optional<ReservaCancha> reservaPadreO = reservaCanchaRepository.findById(reservaDTO.getCodigoReservaPadre());
        if (reservaPadreO.isEmpty()){
            throw new ReservaPadreNoExisteException();
        }
        ReservaCancha reservaPadre = reservaPadreO.get();
        Usuario usuarioPadre = reservaPadre.getUsuario();
        Cancha cancha = reservaPadre.getCancha();
        Date dateFecha = reservaPadre.getFecha();

        LocalDateTime fechaConHora = LocalDateTime.of(dateFecha.toLocalDate(), reservaPadre.getCancha().getCanchaId().getHoraFin());
        if (fechaConHora.isBefore(LocalDateTime.now())) {
            throw new ReservaPosteriorAlFinException();
        }

        ReservaCancha reservaCancha = new ReservaCancha(usuarioPadre, usuario, cancha, dateFecha);
        return reservaCanchaRepository.save(reservaCancha);
    }
}
