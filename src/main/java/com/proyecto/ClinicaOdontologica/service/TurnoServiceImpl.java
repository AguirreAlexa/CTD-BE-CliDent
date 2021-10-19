package com.proyecto.ClinicaOdontologica.service;

import com.proyecto.ClinicaOdontologica.entity.Turno;
import com.proyecto.ClinicaOdontologica.exceptions.BadRequestException;
import com.proyecto.ClinicaOdontologica.repository.ITurnoRepository;
import com.proyecto.ClinicaOdontologica.service.inter.ITurnoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements ITurnoService<Turno> {
    private ITurnoRepository turnoRepository;

    public TurnoServiceImpl(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno agregar(Turno turno) throws BadRequestException {
        if((turnoRepository.findByOdontologoAndHorario(turno.getOdontologo(), turno.getHorario()).isEmpty()) && (turnoRepository.findByPacienteAndHorario(turno.getPaciente(), turno.getHorario()).isEmpty())){
            return turnoRepository.save(turno);
        }else{
            throw new BadRequestException("El paciente o el odontologo ya poseen un turno en este horario");
        }
    }

    @Override
    public void eliminar(Turno turno) {
        if (turno.getHorario() != null) {
            turnoRepository.deleteByHorario(turno.getHorario());
        }
    }

    @Override
    public Optional<Turno> buscar(Long id) {
        return turnoRepository.findById(id);
    }
}

