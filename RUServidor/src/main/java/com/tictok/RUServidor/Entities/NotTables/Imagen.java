package com.tictok.RUServidor.Entities.NotTables;

import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "imagen")
    private Set<Actividad> actividads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "imagen", orphanRemoval = true)
    private Set<Cancha> canchas = new LinkedHashSet<>();

    @Lob
    @Column(name = "imagen_str", nullable = false)
    private String imagenStr;

    public Set<Cancha> getCanchas() {
        return canchas;
    }

    public void setCanchas(Set<Cancha> canchas) {
        this.canchas = canchas;
    }

    public Set<Actividad> getActividads() {
        return actividads;
    }

    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }

    public String getImagenStr() {
        return imagenStr;
    }

    public void setImagenStr(String imagenStr) {
        this.imagenStr = imagenStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
