package br.com.techhub.techstock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.techhub.techstock.controller.espelhos.EquipamentoStatusEnumEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.EquipamentoFiltro;
import br.com.techhub.techstock.controller.requests.EquipamentoRequest;
import br.com.techhub.techstock.controller.requests.EquipamentoStatusRequest;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import br.com.techhub.techstock.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoController implements IController<EquipamentoEspelho, EquipamentoRequest, EquipamentoFiltro> {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<Response<List<EquipamentoEspelho>>> readAll(
        EquipamentoFiltro filtro
    ) {
        Response<List<EquipamentoEspelho>> response = new Response<List<EquipamentoEspelho>>();

        List<Equipamento> list;
        list = equipamentoService.filterBy(filtro);

        // var list = equipamentoService.findAll();
        List<EquipamentoEspelho> listEspelho = new ArrayList<EquipamentoEspelho>();
        for (Equipamento equipamento : list) {
            listEspelho.add(new EquipamentoEspelho(equipamento));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    EquipamentoRequest entity, BindingResult result) {
        Response<Long> response = new Response<>();

        var obj = equipamentoService.save(new Equipamento(entity));
        response.setData(obj.getId());
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
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    EquipamentoRequest request, BindingResult result) {
        Response<Long> response = new Response<>();
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
        response.setData(id);
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

    @GetMapping("/statuses")
    public ResponseEntity<Response<List<EquipamentoStatusEnumEspelho>>> listStatus() {
        Response<List<EquipamentoStatusEnumEspelho>> response = new Response<List<EquipamentoStatusEnumEspelho>>();

        var list = EquipamentoStatus.values();
        List<EquipamentoStatusEnumEspelho> listEspelho = new ArrayList<EquipamentoStatusEnumEspelho>();

        for (EquipamentoStatus equipamentoStatus : list) {
            listEspelho.add(
                new EquipamentoStatusEnumEspelho(equipamentoStatus)
            );
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/change_status")
    public ResponseEntity<Response<Long>> updateStatus(@Valid @RequestBody
    EquipamentoStatusRequest request) {
        Response<Long> response = new Response<>();
        Optional<Equipamento> entityOp = equipamentoService.findById(
            request.id()
        );
        if (!entityOp.isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Equipamento com o id %s não foi encontrado",
                        request.id()
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        var entity = entityOp.get();

        EquipamentoStatus status = null;

        for (EquipamentoStatus currStatus : EquipamentoStatus.values()) {
            if (currStatus.getCodigo() == request.statusId()) {
                status = currStatus;
                break;
            }
        }

        if (status == null) {
            response.getErrors()
                .add(
                    String.format(
                        "Status com o id %s não existe",
                        request.statusId()
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        entity.setStatus(status);

        equipamentoService.save(entity);
        response.setData(request.id());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
