package br.com.techhub.techstock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.TicketRepository;

@Service
public class TicketService extends BaseService<Ticket, TicketRepository> {

    public List<Ticket> findByUsuario(Long usuarioId) {
        return repository.findByUsuario(new Usuario(usuarioId));
    }
}
