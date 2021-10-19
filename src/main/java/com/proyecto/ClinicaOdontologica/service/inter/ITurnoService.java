package com.proyecto.ClinicaOdontologica.service.inter;

import com.proyecto.ClinicaOdontologica.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface ITurnoService <T>{
    T agregar (T t) throws BadRequestException;
    List<T> listar();
    void eliminar(T t);
    Optional<T> buscar(Long id);
}
