package com.proyecto.ClinicaOdontologica.service.inter;

import com.proyecto.ClinicaOdontologica.dto.PacienteDTO;

import java.util.List;
import java.util.Optional;

public interface IPacienteService<T>{
    T agregar (PacienteDTO t);
    T modificar (T t);
    void eliminar (Integer dni);
    Optional<T> buscar(Integer t);
    List<T> listar();
}
