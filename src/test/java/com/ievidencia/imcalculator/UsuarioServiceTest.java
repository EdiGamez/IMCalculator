package com.ievidencia.imcalculator;

import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.UsuarioRepository;
import com.ievidencia.imcalculator.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testValidarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEstatura(1.8);
        usuario.setEdad(20);
        usuario.setNombreUsuario("Usuario1");

        when(usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())).thenReturn(null);

        assertTrue(usuarioService.validarUsuario(usuario));

        Usuario usuario1 = new Usuario();
        usuario.setEstatura(1.8);
        usuario.setEdad(20);
        usuario.setNombreUsuario("Usuario1");

        when(usuarioRepository.findByNombreUsuario(usuario1.getNombreUsuario())).thenReturn(null);

        assertTrue(usuarioService.validarUsuario(usuario1));
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEstatura(1.8);
        usuario.setEdad(20);
        usuario.setNombreUsuario("testUser");
        usuario.setContrasena("testPassword");

        when(usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())).thenReturn(null);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario savedUsuario = usuarioService.saveUsuario(usuario);

        assertNotNull(savedUsuario);
        assertNotEquals("testPassword", savedUsuario.getContrasena());
    }
    @Test
    public void testValidarUsuario_NombreUsuarioDuplicado() {
        // Preparar
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("nombreUsuario");
        usuario.setEdad(20);
        usuario.setEstatura(1.8);

        // Simular que ya existe un usuario con el mismo nombre de usuario
        when(usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())).thenReturn(new Usuario());

        // Probar
        boolean resultado = usuarioService.validarUsuario(usuario);

        // Verificar
        assertFalse(resultado, "El método validarUsuario debería devolver false cuando el nombre de usuario ya existe");
    }

}
