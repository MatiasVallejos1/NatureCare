package com.example.naturecare.entidades;

public class Producto {
    private String nombre, detalle, ficha_tecnica, estado_producto;
    private int cantidad;
    private double monto;

    public Producto(String nombre, String detalle, String ficha_tecnica, String estado_producto, int cantidad, double monto) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.ficha_tecnica = ficha_tecnica;
        this.estado_producto = estado_producto;
        this.cantidad = cantidad;
        this.monto = monto;
    }
    public Producto() {
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
