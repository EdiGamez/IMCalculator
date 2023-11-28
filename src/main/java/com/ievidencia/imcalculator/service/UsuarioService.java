package com.ievidencia.imcalculator.service;


import com.ievidencia.imcalculator.exceptions.UsuarioAlreadyExistsException;
import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.IMCRepository;
import com.ievidencia.imcalculator.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new UsuarioAlreadyExistsException("El nombre de usuario ya existe");
        }

        // Codificar la contraseña
        usuario.setContrasena(hashPassword(usuario.getContrasena()));

        // Guardar el nuevo usuario
        return usuarioRepository.save(usuario);

    }
    public Usuario findByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        // Verificar la contraseña
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuario != null && usuario.getContrasena().equals(hashPassword(contrasena))) {
            return usuario;
        }
        return null;
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public Usuario getUsuarioActual(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioActual");
    }
}