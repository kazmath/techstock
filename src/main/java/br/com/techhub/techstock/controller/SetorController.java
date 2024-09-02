package br.com.techhub.techstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.service.SetorService;

@RestController
@RequestMapping("/api/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;
}
