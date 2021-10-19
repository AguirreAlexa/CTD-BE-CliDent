package com.proyecto.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.ClinicaOdontologica.login.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pacientes")
public class Paciente {

@Id
@SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "paciente_sequence")
    private Long id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private Integer dni;
    private LocalDate fechaAlta;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private User user;
//    @OneToMany(mappedBy = "paciente", cascade = CascadeType.MERGE)
//    private Set<Turno> turnos = new HashSet<>();

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String domicilio, Integer dni,  User user) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = LocalDate.now();
        this.domicilio = domicilio;
        this.user = user;
    }

    public Paciente(String nombre, String apellido, String domicilio, Integer dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

//    public Set<Turno> getTurnos() {
//        return turnos;
//    }
//
//    public void setTurnos(Set<Turno> turnos) { this.turnos = turnos; }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPaciente(Paciente paciente){
        this.nombre = paciente.getNombre();
        this.apellido =paciente.getApellido();
        this.domicilio = paciente.getDomicilio();
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", dni=" + dni +
                ", fechaAlta=" + fechaAlta +
                ", user=" + user +
                '}';
    }
}
