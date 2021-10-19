package com.proyecto.ClinicaOdontologica.service;

import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.repository.IOdontologoRepository;
import com.proyecto.ClinicaOdontologica.service.inter.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IOdontologoService<Odontologo> {
    private final IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        if(odontologo != null){
            return odontologoRepository.save(odontologo);
        }
        return odontologo;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscar(Integer matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }
}
