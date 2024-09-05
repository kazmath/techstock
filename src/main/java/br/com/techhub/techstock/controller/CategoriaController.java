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

import br.com.techhub.techstock.controller.espelhos.CategoriaEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.CategoriaRequest;
import br.com.techhub.techstock.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController implements IController<CategoriaEspelho, CategoriaRequest> {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid @RequestBody
    CategoriaRequest entity, BindingResult result) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'create'"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoriaEspelho>> read(@PathVariable
    Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @GetMapping
    public ResponseEntity<Response<List<CategoriaEspelho>>> readAll(
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
        CategoriaRequest request,
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
