package com.ievidencia.imcalculator.controller;

import com.ievidencia.imcalculator.model.IMC;
import com.ievidencia.imcalculator.model.Usuario;
import com.ievidencia.imcalculator.service.IMCService;
import com.ievidencia.imcalculator.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/imcalculator")
public class IMCController {
    @Autowired
    private IMCService imcService;
    @Autowired
    private UsuarioService usuarioService;
   @PostMapping("/imc/crear")
    public IMC createIMC(@RequestBody IMC imc) {
        return imcService.saveIMC(imc);
    }
    @GetMapping("/imc/{id}")
    public IMC getIMC(@PathVariable Long id) {
        return imcService.getIMC(id);
    }
    @GetMapping("/historial/{usuarioId}")
    public List<IMC> getHistorialIMC(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return  imcService.getHistorialIMC(usuario);
    }
    @GetMapping("/imc")
    public String showIMCForm(Model model) {
        model.addAttribute("imc", new IMC());
        return "imc";
    }

    @PostMapping("/imc")
    public String processIMCForm(IMC imc) {
        imcService.saveIMC(imc);
        return "redirect:/imc";
    }
}