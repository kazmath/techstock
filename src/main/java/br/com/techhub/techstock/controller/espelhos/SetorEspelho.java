package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Setor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetorEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public SetorEspelho(Setor setor) {
        throw new UnsupportedOperationException();
    }

}
