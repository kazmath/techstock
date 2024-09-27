package br.com.techhub.techstock.controller;

import br.com.techhub.techstock.controller.espelhos.AuthEspelho;
import br.com.techhub.techstock.controller.requests.CredentialRequest;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.security.TokenService;
import br.com.techhub.techstock.security.UserSS;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthEspelho> login(@RequestBody @Valid CredentialRequest credentialRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentialRequest.getEmail(), credentialRequest.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserSS) auth.getPrincipal());

        return ResponseEntity.ok(new AuthEspelho(token));
    }
}
