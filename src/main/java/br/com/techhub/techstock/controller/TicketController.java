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
import br.com.techhub.techstock.controller.espelhos.TicketEspelho;
import br.com.techhub.techstock.controller.espelhos.TicketStatusEnumEspelho;
import br.com.techhub.techstock.controller.filters.TicketFiltro;
import br.com.techhub.techstock.controller.requests.TicketRequest;
import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.enums.TicketStatus;
import br.com.techhub.techstock.service.TicketService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ticket")
public class TicketController implements IController<TicketEspelho, TicketRequest, TicketFiltro> {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<Response<List<TicketEspelho>>> readAll(@Valid
    TicketFiltro filtro) {
        Response<List<TicketEspelho>> response = new Response<List<TicketEspelho>>();

        List<Ticket> list;
        list = ticketService.filterBy(filtro);

        List<TicketEspelho> listEspelho = new ArrayList<TicketEspelho>();
        for (Ticket ticket : list) {
            listEspelho.add(new TicketEspelho(ticket));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    TicketRequest entity, BindingResult result) {
        Response<Long> response = new Response<>();

        entity.setStatus(TicketStatus.AGUARDANDO);

        var obj = ticketService.save(new Ticket(entity));
        response.setData(obj.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TicketEspelho>> read(@PathVariable
    Long id) {
        Response<TicketEspelho> response = new Response<TicketEspelho>();

        var obj = ticketService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(
                    String.format("Ticket com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new TicketEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    TicketRequest request, BindingResult result) {
        Response<Long> response = new Response<>();
        if (!ticketService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format("Ticket com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        ticketService.save(new Ticket(request));
        response.setData(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!ticketService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format("Ticket com o id %s não foi encontrada", id)
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ticketService.delete(new Ticket(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/statuses")
    public ResponseEntity<Response<List<TicketStatusEnumEspelho>>> listStatus() {
        Response<List<TicketStatusEnumEspelho>> response = new Response<List<TicketStatusEnumEspelho>>();

        var list = TicketStatus.values();
        List<TicketStatusEnumEspelho> listEspelho = new ArrayList<TicketStatusEnumEspelho>();

        for (TicketStatus ticketStatus : list) {
            listEspelho.add(new TicketStatusEnumEspelho(ticketStatus));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
