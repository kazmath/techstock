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

import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.espelhos.SetorEspelho;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.SetorRequest;
import br.com.techhub.techstock.model.Setor;
import br.com.techhub.techstock.service.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/setor")
public class SetorController implements IController<SetorEspelho, SetorRequest, IFilter> {

    @Autowired
    private SetorService setorService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid @RequestBody
    SetorRequest entity, BindingResult result) {
        Response<Boolean> response = new Response<>();

        var obj = setorService.save(new Setor(entity));
        response.setData(obj.getId() != null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<SetorEspelho>> read(@PathVariable
    Long id) {
        Response<SetorEspelho> response = new Response<SetorEspelho>();

        var obj = setorService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(String.format("Setor com o id %s não foi encontrada", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new SetorEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<SetorEspelho>>> readAll() {
        Response<List<SetorEspelho>> response = new Response<List<SetorEspelho>>();

        var list = setorService.findAll();
        List<SetorEspelho> listEspelho = new ArrayList<SetorEspelho>();
        for (Setor setor : list) {
            listEspelho.add(new SetorEspelho(setor));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Boolean>> update(@PathVariable
    Long id, @Valid @RequestBody
    SetorRequest request, BindingResult result) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!setorService.findById(id).isPresent()) {
            response.getErrors()
                .add(String.format("Setor com o id %s não foi encontrada", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        setorService.save(new Setor(request));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!setorService.findById(id).isPresent()) {
            response.getErrors()
                .add(String.format("Setor com o id %s não foi encontrada", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        setorService.delete(new Setor(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
