package br.com.techhub.techstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService extends BaseService<Movimentacao, MovimentacaoRepository> {

    public List<Movimentacao> findByUsuario(
        Optional<Usuario> usuario
    ) throws Exception {

        if (!usuario.isPresent()) {
            throw new Exception("Usuário não existe");
        }

        var result = repository.findByUsuario(usuario.get());

        return result;
    }
}
