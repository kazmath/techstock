package br.com.techhub.techstock.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.controller.filters.MovimentacaoFiltro;
import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.Usuario;


@Repository
public interface MovimentacaoRepository extends ListCrudRepository<Movimentacao, Long>, RFilter<Movimentacao, MovimentacaoFiltro> {

    List<Movimentacao> findByUsuario(Usuario usuario);
}
