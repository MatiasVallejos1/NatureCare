package com.example.naturecare.entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    int id,tipo;
    String nombre,email,pass,phone;

    public Usuario() {
    }

    public Usuario(int id, int tipo, String nombre, String email, String pass, String phone) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
