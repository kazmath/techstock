package br.com.techhub.techstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.espelhos.UsuarioEspelho;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.UsuarioRequest;
import br.com.techhub.techstock.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController implements IController<UsuarioEspelho, UsuarioRequest> {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid @RequestBody
    UsuarioRequest entity, BindingResult result) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'create'"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UsuarioEspelho>> read(@PathVariable
    Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @GetMapping
    public ResponseEntity<Response<List<UsuarioEspelho>>> readAll(
        @Valid
        IFilter filterObj
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'readAll'"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Boolean>> update(
        @PathVariable
        Long id,
            @RequestBody
        UsuarioRequest request,
        BindingResult result
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'update'"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'delete'"
        );
    }
}
