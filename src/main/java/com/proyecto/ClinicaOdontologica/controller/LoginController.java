package com.proyecto.ClinicaOdontologica.controller;

import com.proyecto.ClinicaOdontologica.login.User;
import com.proyecto.ClinicaOdontologica.login.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<List<?>> login(User user){
        ResponseEntity<List<?>> response = null;

        User user1 = userService.login(user);
        List<Object> body = new ArrayList<>();
        body.add(user1.getToken());
        body.add(user1.getUserRole());
        log.info(user1);
        log.info(user1.getToken());
        response = ResponseEntity.ok().body(body);
        return response;
    }
}
