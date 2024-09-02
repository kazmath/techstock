package br.com.techhub.techstock.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.model.Usuario;

@Repository
public interface UsuarioRepository extends ListCrudRepository<Usuario, Long> {

}
