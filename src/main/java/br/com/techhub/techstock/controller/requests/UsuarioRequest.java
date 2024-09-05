package br.com.techhub.techstock.controller.requests;

import br.com.techhub.techstock.model.Setor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest implements IRequest {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private Boolean admin;

    @NotBlank
    private Setor setor;


}
