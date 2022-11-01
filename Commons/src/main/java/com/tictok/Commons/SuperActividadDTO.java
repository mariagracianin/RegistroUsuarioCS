package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperActividadDTO {
    private String nombreServicio;
    private String nombreCentro;
    private Integer precio;
    private Boolean paseLibre;
    private String address;
    private String barrio;
    private String telefono;
    private String imageString;
    private List<HorarioDTO> horarios;

    public SuperActividadDTO() {
    }

    public SuperActividadDTO(String nombreServicio, String nombreCentro, Integer precio, Boolean paseLibre, String address, String barrio, String telefono, String imageString, List<HorarioDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.paseLibre = paseLibre;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageString = imageString;
        this.horarios = horarios;
    }

    public SuperActividadDTO(String nombreServicio, String nombreCentro, Integer precio, Boolean paseLibre, String address, String barrio, String telefono, String imageString) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.paseLibre = paseLibre;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageString = imageString;
    }

    public static SuperActividadDTO map(Class<SuperActividadDTO> type, Object[] tuple){
        List<Class<?>> tupleTypes = new ArrayList<>();
        for(Object field : tuple){
            System.out.println(field);
            if (field == null){
                System.out.println("Nada");
            }else {
                tupleTypes.add(field.getClass());
            }
        }
        try {
            Constructor<SuperActividadDTO> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addHorario(HorarioDTO horarioDTO){
        horarios.add(horarioDTO);
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Boolean getPaseLibre() {
        return paseLibre;
    }

    public void setPaseLibre(Boolean paseLibre) {
        this.paseLibre = paseLibre;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }


}
