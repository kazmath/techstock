
package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoRequest implements IRequest {


    private Long id;

    @NotNull
    private MovimentacaoTipo tipo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotNull
    private Ticket ticket;

    @NotNull
    private Usuario usuario;

}
