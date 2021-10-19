package com.proyecto.ClinicaOdontologica.dto;

import com.proyecto.ClinicaOdontologica.login.User;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.login.UserRole;

public class PacienteDTO {
    private String nombre;
    private String apellido;
    private String domicilio;
    private Integer dni;
    private String usuario;
    private  String password;
    private Enum role;

    public PacienteDTO() {
    }

    public PacienteDTO(String nombre, String apellido, String domicilio, Integer dni, String usuario, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.usuario = usuario;
        this.password = password;
        this.role = UserRole.USER;
    }

    public User getUser(){
        return new User(usuario, password);
    }

    public Paciente getPaciente(){
        return new Paciente(nombre, apellido, domicilio, dni, getUser());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", dni=" + dni +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
