package com.proyecto.ClinicaOdontologica.service.inter;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService <T>{
    T agregar (T t);
    T modificar (T t);
    Optional<T> buscar(Integer t);
    List<T> listar();
}
