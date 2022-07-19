package com.example.naturecare.entidades;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre, detalle, ficha_tecnica, estado_producto, usuario;
    private int cantidad, id;
    private double monto;

    public Producto(int id, String nombre, int cantidad, String detalle, String ficha_tecnica, String estado_producto, double monto, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.detalle = detalle;
        this.ficha_tecnica = ficha_tecnica;
        this.estado_producto = estado_producto;
        this.monto = monto;
        this.usuario = usuario;
    }
    public Producto() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFicha_tecnica() {
        return ficha_tecnica;
    }

    public void setFicha_tecnica(String ficha_tecnica) {
        this.ficha_tecnica = ficha_tecnica;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
