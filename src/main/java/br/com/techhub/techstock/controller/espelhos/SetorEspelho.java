package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Setor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetorEspelho implements IEspelho {

    private Long id;
    private String nome;

    public SetorEspelho(Setor setor) {
        this.id = setor.getId();
        this.nome = setor.getNome();
    }

}
