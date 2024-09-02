package br.com.techhub.techstock.service;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService extends BaseService<Movimentacao, MovimentacaoRepository> {
}
