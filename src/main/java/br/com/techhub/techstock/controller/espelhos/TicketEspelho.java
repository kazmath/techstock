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
    private Date dt_devolucao;

    private String observacao;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    private Date dt_abertura;

    private TicketStatus       status;
    private UsuarioEspelho     usuario;
    private EquipamentoEspelho equipamento;

    public TicketEspelho(Ticket ticket) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        this.usuario = new UsuarioEspelho(ticket.getUsuario());
        this.equipamento = new EquipamentoEspelho(ticket.getEquipamento());
    }

    public TicketEspelho(Ticket ticket, boolean includeUsuario) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_devolucao();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        this.equipamento = new EquipamentoEspelho(ticket.getEquipamento());
        if (includeUsuario) {
            this.usuario = new UsuarioEspelho(ticket.getUsuario());
        }
    }

}
