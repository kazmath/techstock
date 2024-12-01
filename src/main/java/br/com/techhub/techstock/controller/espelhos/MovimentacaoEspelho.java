package br.com.techhub.techstock.controller.espelhos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEspelho implements IEspelho {

    private Long             id;
    private MovimentacaoTipo tipo;

    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    private Date data;

    private Long ticketId;
    private Long equipamentoId;
    private Long usuarioId;
    private Long usuarioAdmId;

    public MovimentacaoEspelho(Movimentacao movimentacao) {
        this.id = movimentacao.getId();
        this.tipo = movimentacao.getTipo();
        this.data = movimentacao.getDtCreate();
        if (movimentacao.getTicket() != null) {
            TicketEspelho ticketEspelho = new TicketEspelho(
                movimentacao.getTicket(),
                false
            );
            this.ticketId = ticketEspelho.getId();
            this.equipamentoId = ticketEspelho.getEquipamentoId();
        }
        if (movimentacao.getEquipamento() != null) {
            this.equipamentoId = new EquipamentoEspelho(
                movimentacao.getEquipamento()
            ).getId();
        }
        this.usuarioId = new UsuarioEspelho(movimentacao.getUsuario()).getId();
        if (movimentacao.getUsuarioAdm() != null) {
            this.usuarioAdmId = new UsuarioEspelho(movimentacao.getUsuarioAdm())
                .getId();
        }
    }

}
