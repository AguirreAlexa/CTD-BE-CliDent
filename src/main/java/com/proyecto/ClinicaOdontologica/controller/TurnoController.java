package com.proyecto.ClinicaOdontologica.controller;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.entity.Turno;
import com.proyecto.ClinicaOdontologica.exceptions.BadRequestException;
import com.proyecto.ClinicaOdontologica.repository.ITurnoRepository;
import com.proyecto.ClinicaOdontologica.service.OdontologoServiceImpl;
import com.proyecto.ClinicaOdontologica.service.PacienteServiceImpl;
import com.proyecto.ClinicaOdontologica.service.TurnoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    private TurnoServiceImpl turnoService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private OdontologoServiceImpl odontologoService;

    @GetMapping
    public ResponseEntity<?> listar(){
        log.info(turnoService.listar());
        return ResponseEntity.ok(turnoService.listar());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addTurno(@RequestParam("matricula") Integer matricula, @RequestParam("dni") Integer dni, @RequestParam("horario") String horario) throws BadRequestException {

        Turno turno = new Turno();
        LocalDateTime localDateTime = LocalDateTime.parse(horario,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            turno.setHorario(localDateTime);
        Paciente paciente = pacienteService.buscar(dni).get();
            turno.setPaciente(paciente);
        Odontologo odontologo = odontologoService.buscar(matricula).get();
            turno.setOdontologo(odontologo);

        turnoService.agregar(turno);

        log.info(turno.toString());
        return ResponseEntity.ok("Turno guardado correctamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTurno(@PathVariable ("id") Long id) {
        Optional<Turno> t = turnoService.buscar(id);

        ResponseEntity<String> responseEntity = null;
        if (t.isPresent()){
            turnoService.eliminar(t.get());
            responseEntity = ResponseEntity.ok("Turno eliminado");
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;
    }
}
