package com.ievidencia.imcalculator.controller;

import com.ievidencia.imcalculator.model.IMC;
import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.service.IMCService;
import com.ievidencia.imcalculator.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imc")
public class IMCController {
    @Autowired
    private IMCService imcService;
    @Autowired
    private UsuarioService usuarioService;
   @PostMapping
    public IMC createIMC(@RequestBody IMC imc) {
        return imcService.saveIMC(imc);
    }
    @GetMapping("/{id}")
    public IMC getIMC(@PathVariable Long id) {
        return imcService.getIMC(id);
    }

    @GetMapping("/historial/{usuarioId}")
    public List<IMC> getHistorialIMC(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return  imcService.getHistorialIMC(usuario);
    }
}