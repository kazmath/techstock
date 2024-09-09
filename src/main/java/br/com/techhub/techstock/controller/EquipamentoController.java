package br.com.techhub.techstock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.techhub.techstock.controller.espelhos.EquipamentoEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.EquipamentoRequest;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController implements IController<EquipamentoEspelho, EquipamentoRequest, IFilter> {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid @RequestBody
    EquipamentoRequest entity, BindingResult result) {
        Response<Boolean> response = new Response<>();

        var obj = equipamentoService.save(new Equipamento(entity));
        response.setData(obj.getId() != null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Response<EquipamentoEspelho>> read(@PathVariable
    Long id) {
        Response<EquipamentoEspelho> response = new Response<EquipamentoEspelho>();

        var obj = equipamentoService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Equipamento com o id %s não foi encontrado",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new EquipamentoEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<EquipamentoEspelho>>> readAll() {
        Response<List<EquipamentoEspelho>> response = new Response<List<EquipamentoEspelho>>();

        var list = equipamentoService.findAll();
        List<EquipamentoEspelho> listEspelho = new ArrayList<EquipamentoEspelho>();
        for (Equipamento equipamento : list) {
            listEspelho.add(new EquipamentoEspelho(equipamento));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Response<Boolean>> update(@PathVariable
    Long id, @Valid @RequestBody
    EquipamentoRequest request, BindingResult result) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!equipamentoService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Equipamento com o id %s não foi encontrado",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        equipamentoService.save(new Equipamento(request));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!equipamentoService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Equipamento com o id %s não foi encontrado",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        equipamentoService.delete(new Equipamento(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
