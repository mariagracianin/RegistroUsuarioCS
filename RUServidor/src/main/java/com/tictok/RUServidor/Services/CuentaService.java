package com.tictok.RUServidor.Services;

import com.tictok.Commons.BalanceDTO;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Entities.CheckInActividad;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import com.tictok.RUServidor.Repositories.CheckInActividadRepository;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    private final UsuarioRepository usuarioRepository;
    private final CheckInActividadRepository checkInActividadRepository;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository, CheckInActividadRepository checkInActividadRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
        this.checkInActividadRepository = checkInActividadRepository;
        System.out.println("Constuctor Admin");
        crearPrimerAdministrador();
        //crearPrimeraEmpresa();
    }

    private void crearPrimerAdministrador(){
        Cuenta primerAdmin = new Cuenta("admin", "contra","admin");
        cuentaRepository.save(primerAdmin);
        System.out.println("Administrador padre creado");
    }

    //Esto no parece estar bien
    /*private void crearPrimeraEmpresa(){
        Cuenta primeraEmpresa = new Cuenta("empresa@mail", "contra","empresa");
        cuentaRepository.save(primeraEmpresa);
        System.out.println("Empresa 1 creada");
    }*/

    public List<CuentaDTO> findAll() {
        List cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()){
            return null;
        }
        List cuentaDTOList = new ArrayList<CuentaDTO>(cuentaList.size());
        for (int i=0; i<cuentaList.size(); i++){
            Cuenta cuenta = (Cuenta) cuentaList.get(i);
            cuentaDTOList.add(CuentaMapper.toCuentaDTO(cuenta));
        }
        return cuentaDTOList;
    }

    public CuentaDTO save (CuentaDTO newCuentaDTO) throws CuentaYaExisteException {
        Cuenta newCuenta = CuentaMapper.toCuenta(newCuentaDTO);
        Optional<Cuenta> cuenta = cuentaRepository.findById(newCuenta.getId());

        if (cuenta.isPresent()){
            throw new CuentaYaExisteException();
        }
        return CuentaMapper.toCuentaDTO(cuentaRepository.save(newCuenta));
    }

    public Cuenta findOnebyId(String id) throws CuentaNoExisteException {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (cuenta.isPresent()) {
            return cuenta.get();
        }
        else {
            throw new CuentaNoExisteException(id);
        }
    }

    public MiniCuentaDTO autenticar(CuentaDTO cuentaDTOaAutenticar) throws CuentaNoExisteException, PasswordDoesNotMatchException {
        String mailDTO = cuentaDTOaAutenticar.getMail();
        String passwordDTO = cuentaDTOaAutenticar.getPassword();
        Cuenta cuentaConEseMail;


        cuentaConEseMail = findOnebyId(mailDTO);


        if (cuentaConEseMail.getPassword().equals(passwordDTO)){
            return CuentaMapper.toMiniCuentaDTO(cuentaConEseMail);
        }else{
            throw new PasswordDoesNotMatchException();
        }
    }

    @Transactional
    public MegaUsuarioDTO getMegaUsuarioDTOfromMail(String mail) throws CuentaNoExisteException {
        Cuenta cuentaConEseMail = findOnebyId(mail);

        if(cuentaConEseMail.getTipo().equals("user")){
            Usuario usuario = cuentaConEseMail.getUsuario();
            Double gastos = getGastosUsuario(usuario.getCedula());
            MegaUsuarioDTO megaUsuarioDTO = UsuarioMapper.toMegaUsuarioDTO(cuentaConEseMail.getUsuario());
            Double saldo = megaUsuarioDTO.getSaldo() - gastos;
            megaUsuarioDTO.setSaldo(saldo);
            return megaUsuarioDTO;
        }else{
            throw new RuntimeException("LA FUNCION ESTA SOLO TIENE QUE SER PARA CUENTAS DE USUSARIO, LA ESTAS PIDIENDO CON OTRA COSA");
        }

    }
    private Double getGastosUsuario(int cedula){
        LocalDate initial = LocalDate.now();
        Date start = Date.valueOf(initial.withDayOfMonth(1));
        Date end = Date.valueOf(initial.withDayOfMonth(initial.getMonth().length(initial.isLeapYear())));
        List<CheckInActividad> listaCheckInDelMes =
                checkInActividadRepository.findByUsuario_CedulaAndFechaBetween(cedula, start, end);

        Double gastos = 0.0;
        if (!listaCheckInDelMes.isEmpty()){
            for (int i=0; i<listaCheckInDelMes.size(); i++){
                gastos = gastos + listaCheckInDelMes.get(i).getPrecio();
            }
        }
        return gastos;

        //TODO tenemos que agregar el tema del check in de la Cancha
    }


    public List<BalanceDTO> getBalanceCentros(int mes, int year) {
        LocalDate fecha = LocalDate.of(year, mes, 1);
        LocalDate fechaFinalAux = fecha.plusMonths(1);
        Date fechaInicio = Date.valueOf(fecha);
        Date fechaFin = Date.valueOf(fechaFinalAux);

        List<Tuple> tuplasBalances = cuentaRepository.getBalanceAdminCentros(fechaInicio, fechaFin);
        List<BalanceDTO> balanceDTOS = new ArrayList<BalanceDTO>(tuplasBalances.size());

        for (int i = 0; i<tuplasBalances.size(); i++) {
            Tuple tupla = tuplasBalances.get(i);
            BalanceDTO balanceDTO = fromTuplaToBalanceDTO(tupla, "Centro Deportivo");
            balanceDTOS.add(balanceDTO);
        }
        return balanceDTOS;
    }

    public List<BalanceDTO> getBalanceEmpresas(int mes, int year) {
        LocalDate fecha = LocalDate.of(year, mes, 1);
        LocalDate fechaFinalAux = fecha.plusMonths(1);
        Date fechaInicio = Date.valueOf(fecha);
        Date fechaFin = Date.valueOf(fechaFinalAux);

        List<Tuple> tuplasBalances = cuentaRepository.getBalanceAdminEmpresas(fechaInicio, fechaFin);
        List<BalanceDTO> balancesDTO = new ArrayList<BalanceDTO>();

        for (int i = 0; i<tuplasBalances.size(); i++) {
            Tuple tupla = tuplasBalances.get(i);
            BalanceDTO balanceDTO = fromTuplaToBalanceDTO(tupla, "Empresa");
            balancesDTO.add(balanceDTO);
        }

        return balancesDTO;
    }

    private BalanceDTO fromTuplaToBalanceDTO(Tuple tupla, String tipo){
        String nombre;
        String encargado;
        String address;
        String telefono;
        int cantidadCheckIns;
        String rut;
        BigInteger temp;
        double importe;
        nombre = (String) tupla.get("nombre");
        encargado = (String) tupla.get("encargado");
        address = (String) tupla.get("address");
        telefono = (String) tupla.get("telefono");
        rut = (String) tupla.get("rut");
        temp = (BigInteger) tupla.get("cantidad_check_ins");
        cantidadCheckIns = temp.intValue();
        try {
                importe = (double) tupla.get("importe_total");
            } catch  (NullPointerException n) {
            importe = 0.0;
            cantidadCheckIns = 0;
        }
        BalanceDTO balanceDTO = new BalanceDTO(nombre, encargado, address, telefono,
                tipo, rut, cantidadCheckIns, importe);
        if (tipo.equals("Empresa")) {
            temp = (BigInteger) tupla.get("cantidad_usuarios");
            balanceDTO.setCantidadUsuarios(temp.intValue());
        }
        return balanceDTO;
    }
}
