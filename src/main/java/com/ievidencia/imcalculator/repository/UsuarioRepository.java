package com.ievidencia.imcalculator.repository;



import com.ievidencia.imcalculator.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNombreUsuario(String nombreUsuario);
    Usuario findByNombreUsuario(String nombreUsuario);
}