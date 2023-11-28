package com.ievidencia.imcalculator.controller;

import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.service.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/imcalculator")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nombreUsuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Usuario usuario = usuarioService.iniciarSesion(nombreUsuario, contrasena);
        if (usuario != null) {
            session.setAttribute("usuarioActual", usuario);
            model.addAttribute("usuario", usuario);
            return "redirect:/imcalculator/imc";
        } else {
            model.addAttribute("error", "Nombre de Usuario o contraseña incorrecto");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, Model model) {
        System.out.println("Quiero llorar");

        if (usuarioService.existsByNombreUsuario(usuario.getNombreUsuario())) {
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "registro";
        } else {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
            return "redirect:/imcalculator/login";
        }
    }
}