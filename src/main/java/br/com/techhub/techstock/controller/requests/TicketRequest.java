package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequest implements IRequest {

    @JsonIgnore
    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dt_devolucao;

    @NotBlank
    private TicketStatus status;

    @NotNull
    @JsonProperty("usuarioId")
    private Usuario usuario;

    @NotNull
    @JsonProperty("equipamentoId")
    private Equipamento equipamento;


}
