package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO {
    private String mailUsuario;  //si
    private String nombreCentro;
    private String nombreActividad;
    private Double precio;
    private String tipo;
    private HorarioDTO horario;
    private Long codigoReserva;
    private Long codigoReservaPadre; //si
    private String fecha;

    public ReservaDTO(){
    }

    public ReservaDTO(String mailUsuario, String nombreCentro, String nombreActividad, String tipo,
                      HorarioDTO horario, Long codigoReserva, String fecha, Double precio) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
        this.fecha = fecha;
        this.precio = precio;
    }

    public ReservaDTO(String mailUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario,
                      Long codigoReserva, Long codigoReservaPadre, String fecha, Double precio) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
        this.codigoReservaPadre = codigoReservaPadre;
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
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

    public Long getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Long getCodigoReservaPadre() {
        return codigoReservaPadre;
    }

    public void setCodigoReservaPadre(Long codigoReservaPadre) {
        this.codigoReservaPadre = codigoReservaPadre;
    }

    public Double getPrecio() {
        return precio;
    }
}
