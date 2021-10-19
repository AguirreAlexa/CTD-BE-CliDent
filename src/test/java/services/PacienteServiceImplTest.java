package services;

import com.proyecto.ClinicaOdontologica.ClinicaOdontologicaApplication;
import com.proyecto.ClinicaOdontologica.login.User;
import com.proyecto.ClinicaOdontologica.entity.Paciente;
import com.proyecto.ClinicaOdontologica.repository.IPacienteRepository;
import com.proyecto.ClinicaOdontologica.service.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={ClinicaOdontologicaApplication.class})
class PacienteServiceImplTest {
    Logger log = Logger.getLogger(PacienteServiceImplTest.class);

    @Autowired
    PacienteServiceImpl pacienteService;
    @Autowired
    IPacienteRepository pacienteRepository;

    List<Paciente> listaPrueba;
    User user;
    User user1;
    Paciente paciente;
    Paciente paciente1;

    @BeforeEach
    void setUp(){
        user = new User("MartuGomez", "martinita123");
        user1 = new User("LucasR", "perritoLoco");
        paciente = new Paciente("Martina", "Gomez", "Salta 123", 123456,  user);
        paciente1 = new Paciente("Lucas", "Romero",  "Tucuman 456", 123457, user1);
        pacienteRepository.save(paciente);

        listaPrueba = new ArrayList<>();
        listaPrueba.add(paciente);
        listaPrueba.add(paciente1);
    }

    @AfterEach
    void tearDown() {
        List<Paciente> list = pacienteRepository.findAll();
        for (Paciente paciente : list) {
            pacienteRepository.delete(paciente);
        }
    }

    @Test
    void listar() {
        assertNotNull(pacienteService.listar());
        log.info(pacienteService.listar());
        log.info(listaPrueba);
    }

//    @Test
//    void agregar() {
//        pacienteService.agregar(paciente1);
//
//        assertEquals(listaPrueba.toString(), pacienteRepository.findAll().toString());
//        log.info(pacienteRepository.findByDni(paciente.getDni()));
//        log.info(pacienteRepository.findByDni(paciente1.getDni()));
//    }

    @Test
    void modificar() {
        pacienteService.modificar(paciente1);

        assertNotNull(pacienteRepository.findByDni(paciente1.getDni()));
        log.debug(pacienteRepository.findByDni(paciente1.getDni()));
    }

    @Test
    void eliminar() {
        pacienteService.eliminar(123456);

        assertFalse(pacienteRepository.findByDni(123456).isPresent());
        log.info(paciente.toString());
    }

    @Test
    void buscar() {
        Optional<Paciente> buscar = pacienteService.buscar(123456);

        assertEquals(buscar.get().toString(), paciente.toString());
        log.info(pacienteRepository.findByDni(123456).toString());
        log.info(paciente.toString());
    }
}