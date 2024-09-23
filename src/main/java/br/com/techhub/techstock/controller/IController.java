package br.com.techhub.techstock.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.techhub.techstock.controller.espelhos.IEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.IRequest;
import jakarta.validation.Valid;


// @RestController
// @RequestMapping("/api/table_name")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface IController<E extends IEspelho, R extends IRequest, F extends IFilter> {

    // @GetMapping
    public ResponseEntity<Response<List<E>>> readAll(@Valid
    F filtro);

    // @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    R entity, BindingResult result);

    // @GetMapping("/{id}")
    public ResponseEntity<Response<E>> read(@PathVariable
    Long id);

    // @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    R request, BindingResult result);

    // @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id);
}
