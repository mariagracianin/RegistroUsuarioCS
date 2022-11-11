package com.tictok.Commons;

public class CheckInDTO {
    private Integer cedulaUsuario;  // cedula
    private String nombreCentro; //mail centro
    private String nombreActividad;

    private String tipo; //cancha o act?
    private HorarioDTO horario;
    private Long codigoCheckIn; // - codigoCheckIn
    private Long checkInCanchaPadre; //
    private String fecha;
    private Double precio;

    public CheckInDTO(){
    }

    public CheckInDTO(String tipo, Long codigoCheckIn) {
        this.tipo = tipo;
        this.codigoCheckIn = codigoCheckIn;
    }

    public CheckInDTO(Integer cedulaUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario,
                      Long codigoCheckIn, String fecha, Double precio) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoCheckIn = codigoCheckIn;
        this.fecha = fecha;
    }

    public CheckInDTO(Integer mailUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario,
                      Long codigoReserva, Long codigoReservaPadre, String fecha, Double precio) {
        this.cedulaUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoCheckIn = codigoReserva;
        this.checkInCanchaPadre = codigoReservaPadre;
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
        return checkInCanchaPadre;
    }

    public void setCodigoReservaPadre(Long codigoReservaPadre) {
        this.checkInCanchaPadre = codigoReservaPadre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
