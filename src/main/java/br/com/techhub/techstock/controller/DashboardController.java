package br.com.techhub.techstock.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.DashboardEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.EquipamentoFiltro;
import br.com.techhub.techstock.controller.filters.TicketFiltro;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import br.com.techhub.techstock.service.EquipamentoService;
import br.com.techhub.techstock.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "bearerAuth")
public class DashboardController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<Response<DashboardEspelho>> dashboard() {
        var response = new Response<DashboardEspelho>();

        var ticketsTotal = ticketService.filterBy(new TicketFiltro()).size();


        TicketFiltro filtroTicket = new TicketFiltro();
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        filtroTicket.setDt_reserva_begin(start.format(formatter));
        LocalDate end = initial.withDayOfMonth(
            initial.getMonth().length(initial.isLeapYear())
        );
        filtroTicket.setDt_reserva_end(end.format(formatter));
        var ticketsAbertosEsseMes = ticketService.filterBy(filtroTicket).size();


        var equipamentosTotal = equipamentoService.filterBy(
            new EquipamentoFiltro()
        ).size();

        EquipamentoFiltro filtroEquipamento1 = new EquipamentoFiltro();
        filtroEquipamento1.setStatus(
            EquipamentoStatus.EM_MANUTENCAO.toString()
        );
        var equipamentosConserto = equipamentoService.filterBy(
            filtroEquipamento1
        ).size();

        EquipamentoFiltro filtroEquipamento2 = new EquipamentoFiltro();
        filtroEquipamento2.setStatus(EquipamentoStatus.INDISPONIVEL.toString());
        var equipamentosEmUso = equipamentoService.filterBy(filtroEquipamento2)
            .size();

        response.setData(
            new DashboardEspelho(
                ticketsTotal,
                ticketsAbertosEsseMes,
                equipamentosTotal,
                equipamentosConserto,
                equipamentosEmUso
            )
        );

        return ResponseEntity.ok(response);
    }

}
