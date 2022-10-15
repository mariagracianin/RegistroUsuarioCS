package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservarDTO {
    private String mailUsuario;
    private String nombreCentro;
    private String nombreActividad;
    private HorarioDTO horario;

    public ReservarDTO() {
    }

    public ReservarDTO(String mailUsuario, String nombreCentro, String nombreActividad, HorarioDTO horario) {
        this.mailUsuario = mailUsuario;
        this.nombreCentro = nombreCentro;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
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
}
