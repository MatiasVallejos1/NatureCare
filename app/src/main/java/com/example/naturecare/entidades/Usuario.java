package com.example.naturecare.entidades;

public class Usuario {

    private  int id;
    private String nombre;
    private String email;
    private String pass;
    private String phone;
    private int tipo;

    public Usuario(int id, String nombre, String email, String pass, String phone, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.tipo = tipo;
    }
    public Usuario() {
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
