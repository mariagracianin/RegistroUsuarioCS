package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservarDTO {
    private String mailUsuario;
    private String nombreCentro;
    private String nombreActividad;
    private HorarioDTO horario;

    private Long codigoReserva;
    private Long codigoReservaPadre; //Usado en las canchas para pedir el codigo de la reserva del que es padre

    public ReservarDTO() {
    }

    public ReservarDTO(String mailUsuario, String nombreCentro, String nombreActividad, HorarioDTO horario) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
    }

    public ReservarDTO(String mailUsuario, String nombreCentro, String nombreActividad, HorarioDTO horario, Long codigoReserva) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
    }

    public ReservarDTO(String mailUsuario, String nombreCentro, String nombreActividad, HorarioDTO horario, Long codigoReserva, Long codigoReservaPadre) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
        this.codigoReserva = codigoReserva;
        this.codigoReservaPadre = codigoReservaPadre;
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
