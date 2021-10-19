package com.proyecto.ClinicaOdontologica.repository;

import com.proyecto.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(Integer dni);

    @Transactional
    void deleteByDni(Integer dni);
}
