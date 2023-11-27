package com.ievidencia.imcalculator.controller;

import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }
}