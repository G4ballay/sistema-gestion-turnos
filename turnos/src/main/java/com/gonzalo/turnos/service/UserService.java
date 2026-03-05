package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.User;
import com.gonzalo.turnos.exeption.*;
import com.gonzalo.turnos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User modificarUsuario(User user) {

        User usuarioExistente = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsuarioNoExisteException("Usuario no encontrado"));

        Optional<User> usuarioConEmail = userRepository.findByEmail(user.getEmail());

        if(usuarioConEmail.isPresent() && !usuarioConEmail.get().getId().equals(user.getId())){
            throw new EmailYaRegistradoException("El correo electrónico ya está registrado");
        }

        usuarioExistente.setNombre(user.getNombre());
        usuarioExistente.setApellido(user.getApellido());
        usuarioExistente.setEmail(user.getEmail());
        usuarioExistente.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(usuarioExistente);
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
    public List<User> buscarPorApellido(String apellido){
        return userRepository.findByApellidoStartingWithIgnoreCase(apellido);
    }

    @Override
    public void eliminarUsuario(Long id) {

        if(!userRepository.existsById(id)){
            throw new UsuarioNoExisteException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }
}
