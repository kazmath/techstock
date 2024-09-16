package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketStatusEnumEspelho implements IEspelho {

    private char   codigo;
    private String descricao;
    private String corHex;

    public TicketStatusEnumEspelho(TicketStatus ticketStatus) {
        this.codigo = ticketStatus.getCodigo();
        this.descricao = ticketStatus.getDescricao();
        this.corHex = ticketStatus.getCorHex();
    }

}
