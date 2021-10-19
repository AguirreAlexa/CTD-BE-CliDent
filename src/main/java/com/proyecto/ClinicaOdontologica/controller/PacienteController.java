package com.proyecto.ClinicaOdontologica.controller;

import com.proyecto.ClinicaOdontologica.dto.PacienteDTO;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.service.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteServiceImpl pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar(){
        log.info(pacienteService.listar());
        return ResponseEntity.ok(pacienteService.listar());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Paciente> addPaciente(@RequestBody PacienteDTO pacienteDTO){
        log.info(pacienteDTO.toString());
        return ResponseEntity.ok(pacienteService.agregar(pacienteDTO));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updatePaciente(@RequestBody Paciente paciente){
        Paciente p = pacienteService.buscar(paciente.getDni()).get();

        ResponseEntity<String> respuesta = null;
        if (p != null) {
            p.setPaciente(paciente);
            pacienteService.modificar(p);
            respuesta = ResponseEntity.ok("El paciente " + p.getNombre() + " " + p.getApellido() + " fue actualizado con exito");
            log.info(p.toString());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            log.error("Ocurrió un error al modificar");
        }
        return respuesta;
    }

    @DeleteMapping(value = "/delete/{dni}")
    public ResponseEntity<String> deletePaciente(@PathVariable ("dni") Integer dni) {
        Paciente p = pacienteService.buscar(dni).get();

        ResponseEntity<String> respuesta = null;
        if (dni != null && pacienteService.buscar(dni).isPresent()) {
            pacienteService.eliminar(dni);
            respuesta = ResponseEntity.ok("El paciente " + p.getNombre() + " " + p.getApellido() + " ha sido eliminado");
            log.info(p.toString());
        } else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            log.error("Ocurrió un error al eliminar");
        }
        return respuesta;
    }

    @PostMapping("/search")
    public ResponseEntity<Paciente> buscar(Integer dni) {
        ResponseEntity<Paciente> respuesta = null;

        if (dni != null && pacienteService.buscar(dni).isPresent()) {
            respuesta = ResponseEntity.ok(pacienteService.buscar(dni).get());
            log.info(pacienteService.buscar(dni).toString());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            log.error("Ocurrió un error al buscar");
        }
        return respuesta;
    }

}
