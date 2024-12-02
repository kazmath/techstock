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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dt_reserva;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dt_devolucao;

    private String observacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dt_abertura;

    private TicketStatus status;
    private Long         usuarioId;
    private Long         equipamentoId;
    private Long         categoriaEquipId;

    public TicketEspelho(Ticket ticket) {
        this.id = ticket.getId();
        this.dt_reserva = ticket.getDt_reserva();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        this.usuarioId = new UsuarioEspelho(ticket.getUsuario()).getId();
        EquipamentoEspelho equipamentoEspelho = new EquipamentoEspelho(
            ticket.getEquipamento()
        );
        this.equipamentoId = equipamentoEspelho.getId();
        this.categoriaEquipId = equipamentoEspelho.getCategoriaId();
    }

    public TicketEspelho(Ticket ticket, boolean includeUsuario) {
        this.id = ticket.getId();
        this.dt_devolucao = ticket.getDt_reserva();
        this.observacao = ticket.getObservacao();
        this.dt_abertura = ticket.getDtCreate();
        this.status = ticket.getStatus();
        EquipamentoEspelho equipamentoEspelho = new EquipamentoEspelho(
            ticket.getEquipamento()
        );
        this.equipamentoId = equipamentoEspelho.getId();
        this.categoriaEquipId = equipamentoEspelho.getCategoriaId();
        if (includeUsuario) {
            this.usuarioId = new UsuarioEspelho(ticket.getUsuario()).getId();
        }
    }

}
