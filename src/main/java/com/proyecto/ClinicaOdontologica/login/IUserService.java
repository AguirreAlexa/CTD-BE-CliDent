package com.proyecto.ClinicaOdontologica.login;

import java.util.List;
import java.util.Optional;

public interface IUserService<T> {
    T agregar (T t);
    Optional<T> buscar(String usuario);
    List<T> listar();
    T login(T t);
}
