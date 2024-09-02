package br.com.techhub.techstock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techhub.techstock.repository.SetorRepository;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

}
