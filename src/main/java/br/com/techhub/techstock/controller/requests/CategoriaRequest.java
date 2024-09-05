package br.com.techhub.techstock.controller.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaRequest implements IRequest {

    private Long id;

    @NotBlank
    private String nome;

    private String descricao;


}
