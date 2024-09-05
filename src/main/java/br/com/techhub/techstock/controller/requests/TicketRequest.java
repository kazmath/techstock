package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequest implements IRequest {

    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dt_devolucao;

    @NotBlank
    private TicketStatus status;

    @NotBlank
    private Usuario usuario;

    @NotBlank
    private Equipamento equipamento;


}
