package br.com.techhub.techstock.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;

@Repository
public interface TicketRepository extends ListCrudRepository<Ticket, Long> {

    public List<Ticket> findByUsuario(Usuario usuario);
}
