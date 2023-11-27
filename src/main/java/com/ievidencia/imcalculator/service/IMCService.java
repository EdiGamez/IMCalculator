package com.ievidencia.imcalculator.service;

import com.ievidencia.imcalculator.model.IMC;
import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.repository.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMCService {
    @Autowired
    private IMCRepository imcRepository;

    public IMC saveIMC(IMC imc) {
        return imcRepository.save(imc);
    }

    public IMC getIMC(Long id) {
        return imcRepository.findById(id).orElse(null);
    }
    public List<IMC> getHistorialIMC(Usuario usuario){
        return  imcRepository.findByUsuario(usuario);
    }
}