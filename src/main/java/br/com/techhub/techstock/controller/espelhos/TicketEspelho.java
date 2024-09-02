package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public TicketEspelho(Ticket ticket) {
        throw new UnsupportedOperationException();
    }

}
