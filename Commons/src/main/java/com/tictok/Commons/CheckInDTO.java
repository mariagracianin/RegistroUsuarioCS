package com.tictok.Commons;

public class CheckInDTO {
    private Integer cedulaUsuario;  // cedula
    private String nombreCentro; //mail centro
    private String nombreActividad;

    private String tipo;
    private HorarioDTO horario;
    private Long codigoCheckIn; // - codigoCheckIn
    private Long codigoReservaPadre; //
    private String fecha;

    public CheckInDTO(){
    }

    public CheckInDTO(Integer cedulaUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario, Long codigoCheckIn, String fecha) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoCheckIn = codigoCheckIn;
        this.fecha = fecha;
    }

    public CheckInDTO(Integer mailUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario, Long codigoReserva, Long codigoReservaPadre, String fecha) {
        this.cedulaUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoCheckIn = codigoReserva;
        this.codigoReservaPadre = codigoReservaPadre;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(Integer cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public HorarioDTO getHorario() {
        return horario;
    }

    public void setHorario(HorarioDTO horario) {
        this.horario = horario;
    }

    public Long getCodigoCheckIn() {
        return codigoCheckIn;
    }

    public void setCodigoCheckIn(Long codigoCheckIn) {
        this.codigoCheckIn = codigoCheckIn;
    }

    public Long getCodigoReservaPadre() {
        return codigoReservaPadre;
    }

    public void setCodigoReservaPadre(Long codigoReservaPadre) {
        this.codigoReservaPadre = codigoReservaPadre;
    }

}
