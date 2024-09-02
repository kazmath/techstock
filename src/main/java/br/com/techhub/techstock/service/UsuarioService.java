package br.com.techhub.techstock.service;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {
}
