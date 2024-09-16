package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoStatusEnumEspelho implements IEspelho {

    private char   codigo;
    private String descricao;

    public EquipamentoStatusEnumEspelho(EquipamentoStatus equipamentoStatus) {
        this.codigo = equipamentoStatus.getCodigo();
        this.descricao = equipamentoStatus.getDescricao();
    }

}
