package com.ievidencia.imcalculator.service;


import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena) {
        return usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
    }
    public Double calcularIMC(double masaCorporal, Double estatura){
        return masaCorporal/ (estatura * estatura);
    }

}