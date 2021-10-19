package com.proyecto.ClinicaOdontologica.login;

import com.proyecto.ClinicaOdontologica.jwt.Jwt;
import com.proyecto.ClinicaOdontologica.login.User;
import com.proyecto.ClinicaOdontologica.login.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService<User> {
    private IUserRepository userRepository;
    private Jwt jwt;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User agregar(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> buscar(String usuario) {
        return userRepository.findByUsuario(usuario);
    }

    @Override
    public List<User> listar() {
        return userRepository.findAll();
    }

    @Override
    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByUsuario(user.getUsuario());
        if(optionalUser.isPresent()){
            User user1 = optionalUser.get();
            if (user.getPassword().equals(user1.getPassword())){
                if ((user1.getToken() == null) || (LocalDateTime.now().isAfter(user1.getExpirationTime()))) {
                    LocalDateTime dateTime = LocalDateTime.now().plusHours(1);
                    user1.setToken(jwt.getToken(user1, dateTime));
                    user1.setExpirationTime(dateTime);
                }
                user = userRepository.save(user1);
                System.out.println(user);
            }
        }
        return user;
    }
}
