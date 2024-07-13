package com.example.photoapp.Configuracion;

public class photograh {
    private Integer id;
    private String descripcion;
    private String foto;

    public photograh(Integer id, String descripcion, String foto) {
        this.id = id;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
