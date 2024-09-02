package br.com.techhub.techstock.service;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.repository.CategoriaRepository;

@Service
public class CategoriaService extends BaseService<Categoria, CategoriaRepository> {
}
