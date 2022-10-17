package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO {
    private Integer cedulaUsuario;
    private String nombreCentro;
    private String nombreActividad;

    private String tipo;
    private HorarioDTO horario;
    private Long codigoReserva;
    private Long codigoReservaPadre = null; //Usado en las canchas para pedir el codigo de la reserva del que es padre

    public ReservaDTO() {
    }

    public ReservaDTO(Integer cedulaUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
    }

    public ReservaDTO(Integer cedulaUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario, Long codigoReserva) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
    }

    public ReservaDTO(Integer cedulaUsuario, String nombreCentro, String nombreActividad, String tipo, HorarioDTO horario, Long codigoReserva, Long codigoReservaPadre) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.tipo = tipo;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
        this.codigoReservaPadre = codigoReservaPadre;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
}
