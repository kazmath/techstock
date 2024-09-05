package br.com.techhub.techstock.controller.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetorRequest implements IRequest {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String nome;

}
