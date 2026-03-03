package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.User;
import com.gonzalo.turnos.exeption.*;
import com.gonzalo.turnos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User crearUsuario(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailYaRegistradoException("El correo electrónico ya esta registrado");
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }
}
