package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoTipoEnumEspelho implements IEspelho {

    private char   codigo;
    private String descricao;
    // private String corHex;

    public MovimentacaoTipoEnumEspelho(MovimentacaoTipo movimentacaoTipo) {
        this.codigo = movimentacaoTipo.getCodigo();
        this.descricao = movimentacaoTipo.getDescricao();
        // this.corHex = movimentacaoTipo.getCorHex();
    }

}
