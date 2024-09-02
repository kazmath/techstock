package br.com.techhub.techstock.service;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.repository.TicketRepository;

@Service
public class TicketService extends BaseService<Ticket, TicketRepository> {
}
