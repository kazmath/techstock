package br.com.techhub.techstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.service.EquipamentoService;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;
}
