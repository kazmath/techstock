
package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoRequest implements IRequest {

    @JsonIgnore
    private Long id;

    @NotBlank
    private MovimentacaoTipo tipo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotNull
    @JsonProperty("ticketId")
    private Ticket ticket;

    @NotNull
    @JsonProperty("usuarioAdmId")
    private Usuario usuarioAdm;

    @NotNull
    @JsonProperty("usuarioId")
    private Usuario usuario;

}
