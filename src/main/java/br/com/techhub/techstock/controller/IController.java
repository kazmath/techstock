package br.com.techhub.techstock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.techhub.techstock.controller.espelhos.IEspelho;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.IRequest;
import jakarta.validation.Valid;


// @RestController
// @RequestMapping("/api/table_name")
public interface IController<E extends IEspelho, R extends IRequest> {

    // @PostMapping
    public ResponseEntity<Response<E>> create(@Valid @RequestBody
    R entity, BindingResult result);

    // @GetMapping("/{id}")
    public ResponseEntity<Response<E>> read(@PathVariable
    Long id);

    // @GetMapping
    public ResponseEntity<Response<List<E>>> readAll(IFilter filterObj);

    // @PutMapping("/{id}")
    public ResponseEntity<Response<Boolean>> update(@PathVariable
    Long id, @RequestBody
    R request, BindingResult result);

    // @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id);
}
