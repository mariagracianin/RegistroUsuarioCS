package com.tictok.RUServidor.Services;

import com.tictok.Commons.ReservaDTO;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.ReservaCancha;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.ReservaPosteriorAlInicioException;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
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

    public ReservaDTO reservarCancha(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException {
        ReservaCancha reservaCancha;

        //TODO Hay que fijarse que la reserva de la cancha no este hecha todavia
        if (reservaDTO.getCodigoReservaPadre() == null){
            reservaCancha = reservarCanchaPadre(reservaDTO);
            ReservaDTO reservaDTOADevolver = ReservaMapper.fromReservaCanchaToReservaDTO(reservaCancha);
            reservaDTOADevolver.setCodigoReservaPadre(reservaDTOADevolver.getCodigoReserva());
            return reservaDTOADevolver;
        }
        else {
            reservaCancha = reservarCanchaHijo(reservaDTO);
        }

        return null;
    }

    private ReservaCancha reservarCanchaPadre(ReservaDTO reservaDTO) throws UsuarioNoExisteException, ReservaPosteriorAlInicioException {
        Usuario usuario = usuarioRepository.findById(reservaDTO.getCedulaUsuario()).get();
        Horario horarioId = HorarioMapper.fromHorarioDTOToHorario(reservaDTO.getHorario());
        ServicioId canchaId = new ServicioId(reservaDTO.getNombreActividad(), reservaDTO.getNombreCentro(),
                horarioId.getDia(), horarioId.getHoraInicio(), horarioId.getHoraFin());
        Cancha cancha = canchaRepository.getReferenceById(canchaId);
        LocalDate fecha = HorarioMapper.getFecha(horarioId.getDia());
        LocalDateTime fechaConHora = LocalDateTime.of(fecha, horarioId.getHoraInicio());
        if (fechaConHora.isBefore(LocalDateTime.now())){
            throw new ReservaPosteriorAlInicioException();
        }
        ReservaCancha reservaCancha = new ReservaCancha(usuario, usuario, cancha, Date.valueOf(fecha));
        return reservaCanchaRepository.save(reservaCancha);
    }

    private ReservaCancha reservarCanchaHijo(ReservaDTO reservaDTO) {
        return null;
    }
}
