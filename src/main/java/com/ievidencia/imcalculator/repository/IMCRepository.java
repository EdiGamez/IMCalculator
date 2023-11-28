package com.ievidencia.imcalculator.repository;

import com.ievidencia.imcalculator.model.IMC;
import com.ievidencia.imcalculator.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMCRepository extends JpaRepository<IMC, Long> {
    boolean existsById(Long id);
    List<IMC> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
