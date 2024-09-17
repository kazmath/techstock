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

import br.com.techhub.techstock.controller.espelhos.CategoriaEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.CategoriaRequest;
import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController implements IController<CategoriaEspelho, CategoriaRequest, IFilter> {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Response<List<CategoriaEspelho>>> readAll() {
        Response<List<CategoriaEspelho>> response = new Response<List<CategoriaEspelho>>();

        var list = categoriaService.findAll();
        List<CategoriaEspelho> listEspelho = new ArrayList<CategoriaEspelho>();
        for (Categoria categoria : list) {
            listEspelho.add(new CategoriaEspelho(categoria));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    CategoriaRequest entity, BindingResult result) {
        Response<Long> response = new Response<>();

        var obj = categoriaService.save(new Categoria(entity));
        response.setData(obj.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoriaEspelho>> read(@PathVariable
    Long id) {
        Response<CategoriaEspelho> response = new Response<CategoriaEspelho>();

        var obj = categoriaService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Categoria com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new CategoriaEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    CategoriaRequest request, BindingResult result) {
        Response<Long> response = new Response<>();

        if (!categoriaService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Categoria com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        categoriaService.save(new Categoria(request));
        response.setData(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!categoriaService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Categoria com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        categoriaService.delete(new Categoria(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
