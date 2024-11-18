package br.com.techhub.techstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.controller.filters.UsuarioFiltro;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

    public List<Usuario> filterBy(UsuarioFiltro filtro) {
        return repository.filterBy(filtro);
    }

    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    };

}
