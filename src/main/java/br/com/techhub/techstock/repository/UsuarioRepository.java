package br.com.techhub.techstock.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.controller.filters.UsuarioFiltro;
import br.com.techhub.techstock.model.Usuario;

@Repository
public interface UsuarioRepository extends ListCrudRepository<Usuario, Long>, RFilter<Usuario, UsuarioFiltro> {
    Optional<Usuario> findByEmail(String email);
}
