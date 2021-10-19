package com.proyecto.ClinicaOdontologica.repository;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.entity.Turno;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    Optional<Turno> findByPacienteAndHorario(Paciente paciente, LocalDateTime horario);
    Optional<Turno> findByOdontologoAndHorario(Odontologo odontologo, LocalDateTime horario);
    @Transactional
    void deleteByHorario(LocalDateTime horario);
}
