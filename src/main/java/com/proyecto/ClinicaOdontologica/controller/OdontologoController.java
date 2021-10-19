package com.proyecto.ClinicaOdontologica.controller;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.service.OdontologoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoServiceImpl odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> listar(){
        log.info(odontologoService.listar());
        return ResponseEntity.ok(odontologoService.listar());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Odontologo> addOdontologo(@RequestBody Odontologo odontologo){
        log.info(odontologo.toString());
        return ResponseEntity.ok(odontologoService.agregar(odontologo));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateOdontologo(@RequestBody Odontologo odontologo){
        Odontologo o = odontologoService.buscar(odontologo.getMatricula()).get();

        ResponseEntity<String> respuesta = null;
        if (o != null) {
            o.setOdontologo(odontologo);
            odontologoService.modificar(o);
            respuesta = ResponseEntity.ok("El odontologo " + o.getNombre() + " " + o.getApellido() + " fue actualizado con exito");
            log.info(o.toString());
        }else
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            log.error("Ocurrió un error al modificar");

        return respuesta;
    }

    @PostMapping("/search")
    public ResponseEntity<Odontologo> buscar(Integer matricula) {
        ResponseEntity<Odontologo> respuesta = null;

        if (matricula != null && odontologoService.buscar(matricula).isPresent()) {
            respuesta = ResponseEntity.ok(odontologoService.buscar(matricula).get());
            log.info(odontologoService.buscar(matricula).toString());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            log.error("Ocurrió un error al buscar");
        }
        return respuesta;
    }
}
