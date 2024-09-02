package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Movimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public MovimentacaoEspelho(Movimentacao movimentacao) {
        throw new UnsupportedOperationException();
    }

}
