package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Equipamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public EquipamentoEspelho(Equipamento equipamento) {
        throw new UnsupportedOperationException();
    }

}
