package br.com.techhub.techstock.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.techhub.techstock.model.Ticket;

@Repository
public interface TicketRepository extends ListCrudRepository<Ticket, Long> {

}
