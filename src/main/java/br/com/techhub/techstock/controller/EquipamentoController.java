package br.com.techhub.techstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.EquipamentoEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.EquipamentoRequest;
import br.com.techhub.techstock.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController implements IController<EquipamentoEspelho, EquipamentoRequest> {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid
    EquipamentoRequest entity, BindingResult result) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'create'"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<EquipamentoEspelho>> read(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @GetMapping
    public ResponseEntity<Response<List<EquipamentoEspelho>>> readAll(
        IFilter filterObj
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'readAll'"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Boolean>> update(
        Long id,
        EquipamentoRequest request,
        BindingResult result
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'update'"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'delete'"
        );
    }
}
