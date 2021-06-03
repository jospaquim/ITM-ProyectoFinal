package com.example.itm_proyectofinal.Beans;

public class Usuario {
    private int ID;
    private int DNI;
    private String Nombres;
    private String Apellidos;
    private int Celular;
    private String Correo;
    private String Direcion;
    private String Password;


    public Usuario() {
    }

    public Usuario(int ID, int DNI, String nombres, String apellidos, int celular, String correo, String direcion, String password) {
        this.ID = ID;
        this.DNI = DNI;
        Nombres = nombres;
        Apellidos = apellidos;
        Celular = celular;
        Correo = correo;
        Direcion = direcion;
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int celular) {
        Celular = celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDirecion() {
        return Direcion;
    }

    public void setDirecion(String direcion) {
        Direcion = direcion;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
