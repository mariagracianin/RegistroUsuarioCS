package com.tictok.RUServidor.Services;

import com.tictok.Commons.CheckInDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Exceptions.CuentaNoExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import com.tictok.RUServidor.Mappers.CheckInMapper;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.*;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final EmpresaRepository empresaRepository;
    private final ReservaActividadRepository reservaActividadRepository;
    private final ReservaCanchaRepository reservaCanchaRepository;
    private final EmpresaService empresaService;
    private final CuentaService cuentaService;
    private final CentroService centroService;
    private final CheckInActividadRepository checkInActividadRepository;
    private final CheckInCanchaRepository checkInCanchaRepository;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CuentaRepository cuentaRepository,
                          EmpresaRepository empresaRepository, ReservaActividadRepository reservaActividadRepository, ReservaCanchaRepository reservaCanchaRepository, EmpresaService empresaService, CuentaService cuentaService, CentroService centroService, CheckInActividadRepository checkInActividadRepository, CheckInCanchaRepository checkInCanchaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
        this.empresaRepository = empresaRepository;
        this.reservaActividadRepository = reservaActividadRepository;
        this.reservaCanchaRepository = reservaCanchaRepository;
        this.empresaService = empresaService;
        this.cuentaService = cuentaService;
        this.centroService = centroService;
        this.checkInActividadRepository = checkInActividadRepository;
        this.checkInCanchaRepository = checkInCanchaRepository;
    }

    public List<UsuarioDTO> findAll() {
        List usuarioList = usuarioRepository.findAll();
        if (usuarioList.isEmpty()){
            return null;
        }

        List usuarioDTOList = new ArrayList<UsuarioDTO>(usuarioList.size());
        for (int i=0; i<usuarioList.size(); i++){
            Usuario user = (Usuario) usuarioList.get(i);
            usuarioDTOList.add(UsuarioMapper.toUsuarioDTO(user));
        }
        return usuarioDTOList;
    }


    public UsuarioDTO findOnebyId(Integer id) throws UsuarioNoExisteException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return UsuarioMapper.toUsuarioDTO(usuario.get());
        }
        else {
            throw new UsuarioNoExisteException(id);
        }
    }

    public Usuario findOnebyId2(Integer id) throws UsuarioNoExisteException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        else {
            throw new UsuarioNoExisteException(id);
        }
    }

    public List<Usuario> findbyId(List<Integer> idList){
        return usuarioRepository.findAllById(idList);
    }

    public Usuario save(UsuarioDTO newUsuarioDTO) {
        Usuario newUsuario = UsuarioMapper.toUsuario(newUsuarioDTO);
            Optional<Usuario> user = usuarioRepository.findById(newUsuario.getCedula());

            if (!newUsuario.telefonoCorrecto()){
                throw new UsuarioMalDefinido();
            }
            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new UsuarioYaExisteException(newUsuario.getStringCuenta());
            } else{
                System.out.println("No lo encontro");
                }

        return usuarioRepository.save(newUsuario);

    }

    //@Transactional
    public Usuario saveNewUsurio(MegaUsuarioDTO megaUsuarioDTO, String mail) throws Exception {
        Cuenta cuenta = CuentaMapper.toCuentaFromMegaUsuarioDTO(megaUsuarioDTO);
        Usuario usuario = UsuarioMapper.toUsuarioFromMegaUsuarioDTO(megaUsuarioDTO);
        usuario.setCuenta(cuenta);
        cuenta.setUsuario(usuario);
        cuentaRepository.save(cuenta);
        usuario.setEmpresa(cuentaService.findOnebyId(mail).getEmpresa());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public List<ReservaDTO> getReservasByUsuario(String mail) {
        Date fecha = Date.valueOf(LocalDate.now());
        List<ReservaActividad> actividadesReservadas =
                reservaActividadRepository.findActividadesReservadasDespuesDe(mail, fecha);
        List<ReservaCancha> canchasReservadas = reservaCanchaRepository.findCanchasReservadasDespuesDe(mail, fecha);

        return ReservaMapper.fromListReservasToReservaDTO(canchasReservadas, actividadesReservadas);
    }

    @Transactional
    public List<ReservaDTO> getReservasActuales(String mailUsuario) throws CuentaNoExisteException {
        Usuario usuario = cuentaService.findOnebyId(mailUsuario).getUsuario();
        List<ReservaActividad> reservasActividades = reservaActividadRepository.conseguirReservasEntreFechasYDeUsuario(usuario.getCedula(), Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(6)));
        List<ReservaCancha> reservaCanchas = reservaCanchaRepository.conseguirReservasEntreFechasYDeUsuario(usuario.getCedula(),Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(6)));
        List<ReservaDTO> reservaDTOlist = ReservaMapper.fromListReservasToReservaDTO(reservaCanchas,reservasActividades);
        return reservaDTOlist;
    }
    @Transactional
    public List<ReservaDTO> getReservasUsuarioByCedulaAndCentro(int cedula, String mailCentro) throws CuentaNoExisteException {
        CentroDeportivo centro = cuentaService.findOnebyId(mailCentro).getCentroDeportivo();
        String nombreCentro = centro.getNombreCentro();

        List<ReservaActividad> reservasActividades = reservaActividadRepository.conseguirReservasEntreFechasYDeUsuario(cedula, Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(6)));
        List<ReservaCancha> reservaCanchas = reservaCanchaRepository.conseguirReservasEntreFechasYDeUsuario(cedula,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(6)));

        List<ReservaDTO> reservaDTOlist = ReservaMapper.fromListReservasToReservaDTO(reservaCanchas,reservasActividades);

        List<ReservaDTO> reservaDTOListFromCentro = new ArrayList<ReservaDTO>();
        for(int i=0; i<reservaDTOlist.size(); i++){
            if(reservaDTOlist.get(i).getNombreCentro().equals(nombreCentro)){
                reservaDTOListFromCentro.add(reservaDTOlist.get(i));
            }
        }
        System.out.println("-------------------estoooooooooooooooooooooo"+ reservaDTOListFromCentro.size());
        return reservaDTOListFromCentro;
    }

    public ReservaDTO getReservaCanchaFromCodigo(String codigoPadre) throws Exception {
        Optional<ReservaCancha> reservaCancha = reservaCanchaRepository.findById(Long.parseLong(codigoPadre));
        if (reservaCancha.isEmpty()) {
            throw new Exception();
        }
        return ReservaMapper.fromReservaCanchaToReservaDTO(reservaCancha.get());
    }

    public List<CheckInDTO> getCheckIns(int cedula, int mes, int year){
        LocalDate fecha1 = LocalDate.of(year, mes, 1);
        LocalDate fecha2= fecha1.plusMonths(1);
        Date fechaInicio = Date.valueOf(fecha1);
        Date fechaFin = Date.valueOf(fecha2);

        List<CheckInCancha> checkInsCanchas = checkInCanchaRepository.findByUsuario_CedulaAndFechaBetween(cedula,fechaInicio,fechaFin);
        List<CheckInActividad> checkInsActividads = checkInActividadRepository.findByUsuario_CedulaAndFechaBetween(cedula,fechaInicio,fechaFin);

        List<CheckInDTO> checkInDTOList = CheckInMapper.fromListsCheckInsToCheckInDTO(checkInsCanchas,checkInsActividads);


        return checkInDTOList;
    }

    public Double getGastosMes(int cedula, int mes, int year){
        List<CheckInDTO> checkInDTOList= getCheckIns(cedula, mes, year);
        Double gastos = 0.0;
        for (int i = 0; i < checkInDTOList.size(); i++){
            gastos += checkInDTOList.get(i).getPrecio();
        }
        return gastos;
    }

}
