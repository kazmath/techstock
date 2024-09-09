package br.com.techhub.techstock.controller.espelhos;

import java.util.Date;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketEspelho implements IEspelho {

    private Long id;
    private Date         dt_devolucao;
    private TicketStatus status;
    private Long         usuarioId;
    private Long         equipamentoId;

    public TicketEspelho(Ticket ticket) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.status = ticket.getStatus();
        this.usuarioId = ticket.getUsuario().getId();
        this.equipamentoId = ticket.getEquipamento().getId();
    }

}
