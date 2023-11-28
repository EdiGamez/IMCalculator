package com.ievidencia.imcalculator.service;

import com.ievidencia.imcalculator.model.IMC;
import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IMCService {
    private final IMCRepository imcRepository;

    @Autowired
    public IMCService(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public IMC guardarIMC(IMC imc) {
        return imcRepository.save(imc);
    }


    public List<IMC> obtenerHistorialIMC(Usuario usuario) {
        return imcRepository.findByUsuarioOrderByFechaDesc(usuario);
    }
    public boolean existsById(Long id){
        return imcRepository.existsById(id);
    }
}