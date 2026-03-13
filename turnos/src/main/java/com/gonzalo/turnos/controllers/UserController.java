package com.gonzalo.turnos.controllers;

import com.gonzalo.turnos.entity.User;
import com.gonzalo.turnos.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/usuarios")
    public List<User> listarUsuarios(){
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<User> usuarioPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }

    @GetMapping("/buscar/apellido")
    public List<User> buscarPorApellido(@RequestParam String apellido) {
        return userService.buscarPorApellido(apellido);
    }

    @GetMapping("/buscar/email")
    public Optional<User> buscarPorEmail(@RequestParam String email) {
        return userService.buscarPorEmail(email);
    }
}


