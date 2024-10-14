package br.com.techhub.techstock.controller.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialRequest implements IRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
