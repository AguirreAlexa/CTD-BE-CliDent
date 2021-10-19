package com.proyecto.ClinicaOdontologica.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Turnos")
public class Turno {

    @Id
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "turno_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName= "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "idOdontologo", referencedColumnName= "id")
    private Odontologo odontologo;

    private LocalDateTime horario;

    public Turno() {
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime horario) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", horario=" + horario +
                '}';
    }
}
