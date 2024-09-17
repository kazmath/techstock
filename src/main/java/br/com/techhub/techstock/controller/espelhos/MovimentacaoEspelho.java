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
    private Date             data;

    private TicketEspelho  ticket;
    private UsuarioEspelho usuario;

    public MovimentacaoEspelho(Movimentacao movimentacao) {
        this.id = movimentacao.getId();
        this.tipo = movimentacao.getTipo();
        this.data = movimentacao.getData();
        this.ticket = new TicketEspelho(movimentacao.getTicket());
        this.usuario = new UsuarioEspelho(movimentacao.getUsuario());
    }

}
