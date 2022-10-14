package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperActividadDTO {
    private String nombreServicio;
    private Integer precio;
    private Integer cupos;
    private Boolean paseLibre;
    private String nombreCentro;
    private String address;
    private String barrio;
    private String telefono;
    private List<HorarioDTO> horarios;


    public SuperActividadDTO() {
    }



}
