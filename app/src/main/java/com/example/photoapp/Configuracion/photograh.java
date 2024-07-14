package com.example.photoapp.Configuracion;

import android.graphics.Bitmap;

public class photograh {
    private Integer id;
    private String descripcion;
    private Bitmap foto;

    public photograh(Integer id, String descripcion, Bitmap foto) {
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

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String toString(){
        return "ID: " + this.id + ", Descripcion: " + this.descripcion;
    }
}
