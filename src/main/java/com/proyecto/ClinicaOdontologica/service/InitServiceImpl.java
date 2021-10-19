package com.proyecto.ClinicaOdontologica.service;

import com.proyecto.ClinicaOdontologica.dto.PacienteDTO;
import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.entity.Turno;
import com.proyecto.ClinicaOdontologica.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class InitServiceImpl implements ApplicationRunner {
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private OdontologoServiceImpl odontologoService;
    @Autowired
    private TurnoServiceImpl turnoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Odontologo dentist = new Odontologo("Julian", "Gomez", 12346);

        PacienteDTO pacient = new PacienteDTO("Alexa", "Aguirre", "Salta 123", 39637402, "AlexaAguirre", "12345");


        Paciente paciente = pacient.getPaciente();

        LocalDateTime ahora = LocalDateTime.now();
        Turno turno = new Turno( pacienteService.agregar(pacient), odontologoService.agregar(dentist), ahora);
        turnoService.agregar(turno);
    }
}
