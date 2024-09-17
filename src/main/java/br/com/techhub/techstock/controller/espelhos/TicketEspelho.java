package br.com.techhub.techstock.controller.espelhos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketEspelho implements IEspelho {

    private Long id;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    private Date         dt_devolucao;

    private TicketStatus status;
    private UsuarioEspelho     usuarioId;
    private EquipamentoEspelho equipamentoId;

    public TicketEspelho(Ticket ticket) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.status = ticket.getStatus();
        this.usuarioId = new UsuarioEspelho(ticket.getUsuario());
        this.equipamentoId = new EquipamentoEspelho(ticket.getEquipamento());
    }

}
