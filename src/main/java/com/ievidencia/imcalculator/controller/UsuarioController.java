package com.ievidencia.imcalculator.controller;

import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/imcalculator")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(Usuario usuario) {
        Usuario existing = usuarioService.findByNombreUsuarioAndContrasena(usuario.getNombreUsuario(), usuario.getContrasena());
        if (existing != null) {
            return "redirect:/imc";
        } else {
            return "login";
        }
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String processRegistrationForm(Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/login";
    }
}