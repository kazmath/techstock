package br.com.techhub.techstock.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.model.Setor;

@Repository
public interface SetorRepository extends ListCrudRepository<Setor, Long> {

}
