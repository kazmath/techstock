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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dt_reserva;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dt_devolucao;

    private String observacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dt_abertura;

    private TicketStatus status;
    private Long         usuarioId;
    private Long         equipamentoId;

    public TicketEspelho(Ticket ticket) {
        this.id = ticket.getId();
        this.dt_reserva = ticket.getDt_reserva();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        this.usuarioId = new UsuarioEspelho(ticket.getUsuario()).getId();
        this.equipamentoId = new EquipamentoEspelho(ticket.getEquipamento())
            .getId();
    }

    public TicketEspelho(Ticket ticket, boolean includeUsuario) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_reserva();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        this.equipamentoId = new EquipamentoEspelho(ticket.getEquipamento())
            .getId();
        if (includeUsuario) {
            this.usuarioId = new UsuarioEspelho(ticket.getUsuario()).getId();
        }
    }

}
