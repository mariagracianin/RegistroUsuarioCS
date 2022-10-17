package com.tictok.RUServidor.Services;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.ReservaActividad;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuposAgotadosException;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.HorarioMapper;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.ActividadRepository;
import com.tictok.RUServidor.Repositories.ReservaActividadRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActividadService {

    private final UsuarioRepository usuarioRepository;
    private final ActividadRepository actividadRepository;
    private final ReservaActividadRepository reservaActividadRepository;

    @Autowired
    public ActividadService(UsuarioRepository usuarioRepository, ActividadRepository actividadRepository, ReservaActividadRepository reservaActividadRepository) {
        this.usuarioRepository = usuarioRepository;
        this.actividadRepository = actividadRepository;
        this.reservaActividadRepository = reservaActividadRepository;
    }

    public ReservaDTO reservarActividad(ReservaDTO reservaDTO) throws CuposAgotadosException {
        Usuario usuario = usuarioRepository.findById(reservaDTO.getCedulaUsuario()).get();
        Horario horarioId = HorarioMapper.fromHorarioDTOToHorario(reservaDTO.getHorario());
        ServicioId actividadId = new ServicioId(reservaDTO.getNombreActividad(), reservaDTO.getNombreCentro(),
                horarioId.getDia(), horarioId.getHoraInicio(), horarioId.getHoraFin());
        Actividad actividad = actividadRepository.getReferenceById(actividadId);

        LocalDate fecha = HorarioMapper.getFecha(horarioId.getDia());
        Date dateFecha = Date.valueOf(fecha);

        if (actividad.getCupos()!= -1){
//            long cantidadDeReservas = reservaActividadRepository.countReservasActividadConFechaYHora(actividadId.getNombreServicio(),
//                    actividadId.getCentroDeportivo(), actividadId.getHoraInicio(), dateFecha);
//            if (cantidadDeReservas >= actividad.getCupos()){
//                throw new CuposAgotadosException();
//            }
        }

        ReservaActividad reservaActividad = new ReservaActividad(usuario, dateFecha, actividad);
        reservaActividad = reservaActividadRepository.save(reservaActividad);

        return ReservaMapper.fromReservaActividadToReservaDTO(reservaActividad);
    }

    public List<SuperActividadDTO> findAll() {
        List actividadList = actividadRepository.findAll();
        if (actividadList.isEmpty()){
            return null;
        }
        //separar el all en listas de las que van en un mismo grupo
        //separar por centro?
        List actividadDTOList = new ArrayList<SuperActividadDTO>(actividadList.size());
        for (int i=0; i<actividadList.size(); i++){


            Cuenta cuenta = (Cuenta) actividadList.get(i);
            actividadDTOList.add(CuentaMapper.toCuentaDTO(cuenta));
        }
        return actividadDTOList;
    }
}
