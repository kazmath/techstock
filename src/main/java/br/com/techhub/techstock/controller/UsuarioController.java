package br.com.techhub.techstock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.AuthEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.espelhos.UsuarioEspelho;
import br.com.techhub.techstock.controller.filters.UsuarioFiltro;
import br.com.techhub.techstock.controller.requests.CredentialRequest;
import br.com.techhub.techstock.controller.requests.UsuarioRequest;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.security.TokenService;
import br.com.techhub.techstock.security.UserSS;
import br.com.techhub.techstock.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuario")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController implements IController<UsuarioEspelho, UsuarioRequest, UsuarioFiltro> {
    private UsuarioService              usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService          tokenService;

    /**
     * @param usuarioService
     * @param authenticationManager
     * @param tokenService
     */
    public UsuarioController(
        UsuarioService usuarioService,
        AuthenticationManager authenticationManager,
        TokenService tokenService
    ) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Response<List<UsuarioEspelho>>> readAll(
        UsuarioFiltro filtro
    ) {
        Response<List<UsuarioEspelho>> response = new Response<List<UsuarioEspelho>>();

        List<Usuario> list = usuarioService.filterBy(filtro);
        List<UsuarioEspelho> listEspelho = new ArrayList<UsuarioEspelho>();
        for (Usuario usuario : list) {
            listEspelho.add(new UsuarioEspelho(usuario));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    UsuarioRequest entity, BindingResult result) {
        Response<Long> response = new Response<>();

        var obj = usuarioService.save(new Usuario(entity));
        response.setData(obj.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UsuarioEspelho>> read(@PathVariable
    Long id) {
        Response<UsuarioEspelho> response = new Response<UsuarioEspelho>();

        var obj = usuarioService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(
                    String.format("Usuario com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new UsuarioEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    UsuarioRequest request, BindingResult result) {
        Response<Long> response = new Response<>();
        if (!usuarioService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format("Usuario com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        usuarioService.save(new Usuario(request));
        response.setData(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!usuarioService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format("Usuario com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        usuarioService.delete(new Usuario(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Response<AuthEspelho>> login(@RequestBody @Valid
    CredentialRequest credentialRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
            credentialRequest.getEmail(),
            credentialRequest.getPassword()
        );
        var response = new Response<AuthEspelho>();

        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserSS) auth.getPrincipal());

        response.setData(new AuthEspelho(token));
        return ResponseEntity.ok(response);
    }

}
