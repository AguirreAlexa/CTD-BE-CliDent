package services;

import com.proyecto.ClinicaOdontologica.ClinicaOdontologicaApplication;
import com.proyecto.ClinicaOdontologica.entity.Odontologo;
import com.proyecto.ClinicaOdontologica.repository.IOdontologoRepository;
import com.proyecto.ClinicaOdontologica.service.OdontologoServiceImpl;
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
class OdontologoServiceImplTest {
 Logger log = Logger.getLogger(OdontologoServiceImplTest.class);

   @Autowired
    OdontologoServiceImpl odontologoService;
   @Autowired
    IOdontologoRepository odontologoRepository;

    List<Odontologo> listaPrueba;
    Odontologo odontologo;
    Odontologo odontologo1;

   @BeforeEach
   void setUp(){
      odontologo = new Odontologo("Lara", "Gonzalez", 1234);
      odontologo1 = new Odontologo("Leandro", "Ramirez", 1235);
      odontologoService.agregar(odontologo);

      listaPrueba = new ArrayList<>();
           listaPrueba.add(odontologo);
           listaPrueba.add(odontologo1);
   }

    @AfterEach
    void tearDown() {
        List<Odontologo> list = odontologoRepository.findAll();
        for (Odontologo odontologo : list) {
            odontologoRepository.delete(odontologo);
        }
    }

   @Test
    void listar() {
       assertNotNull(odontologoService.listar());
       log.info(odontologoService.listar());
       log.info(listaPrueba);
    }

    @Test
    void agregar() {
        odontologoService.agregar(odontologo1);

        assertEquals(listaPrueba.toString(), odontologoRepository.findAll().toString());
        log.info(odontologoRepository.findByMatricula(odontologo.getMatricula()));
        log.info(odontologoRepository.findByMatricula(odontologo1.getMatricula()));
    }

    @Test
    void modificar() {
       odontologoService.modificar(odontologo1);

       assertNotNull(odontologoRepository.findByMatricula(odontologo.getMatricula()));
       log.debug(odontologoRepository.findByMatricula(odontologo.getMatricula()));
    }

    @Test
    void buscar() {
        Optional<Odontologo> buscar = odontologoService.buscar(1234);

        assertEquals(buscar.get().toString(), odontologo.toString());
        log.info(odontologoRepository.findByMatricula(1234).toString());
        log.info(odontologo.toString());
    }
}