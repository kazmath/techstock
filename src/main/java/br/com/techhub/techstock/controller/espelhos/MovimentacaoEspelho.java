package br.com.techhub.techstock.controller.espelhos;

import java.util.Date;

import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoEspelho implements IEspelho {

    private Long             id;
    private MovimentacaoTipo tipo;
    private Date             data;
    private TicketEspelho    ticketId;
    private UsuarioEspelho   usuarioId;

    public MovimentacaoEspelho(Movimentacao movimentacao) {
        this.id = movimentacao.getId();
        this.tipo = movimentacao.getTipo();
        this.data = movimentacao.getData();
        this.ticketId = new TicketEspelho(movimentacao.getTicket());
        this.usuarioId = new UsuarioEspelho(movimentacao.getUsuario());
    }

}
