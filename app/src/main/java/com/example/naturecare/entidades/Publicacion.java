package com.example.naturecare.entidades;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Publicacion {
    int idPublicacion,like,comentarios;
    String nombre,detalle;
    Date hora;

    public Publicacion(int idPublicacion, String nombre, String detalle, int like, int comentarios, Date hora) {
        this.idPublicacion = idPublicacion;
        this.nombre = nombre;
        this.detalle = detalle;
        this.like = like;
        this.comentarios = comentarios;
        this.hora = hora;
    }

    public Publicacion() {
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public void obtenerHora(Date date){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        simpleDate.format(date);
    }
}
