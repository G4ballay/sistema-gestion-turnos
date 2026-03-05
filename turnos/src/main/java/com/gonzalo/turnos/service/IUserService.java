package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User crearUsuario(User user);

    User modificarUsuario(User user);

    List<User> listarUsuarios();

    Optional<User> buscarPorId(Long id);

    Optional<User> buscarPorEmail(String correoElectronico);

    public List<User> buscarPorApellido(String apellido);

    void eliminarUsuario(Long id);
}

