package com.proyecto.ClinicaOdontologica.service;

import com.proyecto.ClinicaOdontologica.dto.PacienteDTO;
import com.proyecto.ClinicaOdontologica.login.UserServiceImpl;
import com.proyecto.ClinicaOdontologica.login.User;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.repository.IPacienteRepository;
import com.proyecto.ClinicaOdontologica.service.inter.IPacienteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService<Paciente> {
    private final IPacienteRepository pacienteRepository;
    private UserServiceImpl userService;

    public PacienteServiceImpl(IPacienteRepository pacienteRepository, UserServiceImpl userService) {
        this.pacienteRepository = pacienteRepository;
        this.userService = userService;
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente agregar(PacienteDTO pacienteDTO) {
        if(pacienteDTO != null && (pacienteRepository.findByDni(pacienteDTO.getDni()).isEmpty())){
            Paciente paciente = pacienteDTO.getPaciente();
            paciente.setFechaAlta(LocalDate.now());
            paciente.setUser(userService.agregar(paciente.getUser()));
            return pacienteRepository.save(paciente);
        }
        return null;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminar(Integer dni) {
        if(pacienteRepository.findByDni(dni).isPresent()){
            pacienteRepository.deleteByDni(dni);
        }
    }

    @Override
    public Optional<Paciente> buscar(Integer dni) {
        return pacienteRepository.findByDni(dni);
    }
}
