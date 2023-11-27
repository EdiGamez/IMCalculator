package com.ievidencia.imcalculator.service;


import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        if (validarUsuario(usuario)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setContrasena(encoder.encode(usuario.getContrasena()));
            return usuarioRepository.save(usuario);
        } else {
            // Manejo de error cuando los datos del usuario no son válidos
            throw new IllegalArgumentException("Los datos del usuario no son válidos");
        }
    }
    public boolean validarUsuario(Usuario usuario) {
        boolean estaturaValida = usuario.getEstatura() >= 1 && usuario.getEstatura() <= 2.5;
        boolean edadValida = usuario.getEdad() > 15;
        boolean nombreUsuarioUnico = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()) == null;
        return estaturaValida && edadValida && nombreUsuarioUnico;
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