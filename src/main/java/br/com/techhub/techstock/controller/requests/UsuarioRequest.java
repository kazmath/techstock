package br.com.techhub.techstock.controller.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.techhub.techstock.model.Setor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequest implements IRequest {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String codigo;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private Boolean admin;

    @NotNull
    @JsonProperty("setorId")
    private Setor setor;


}
