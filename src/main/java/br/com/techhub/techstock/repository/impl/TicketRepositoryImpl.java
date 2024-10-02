package br.com.techhub.techstock.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.techhub.techstock.controller.filters.TicketFiltro;
import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.repository.RFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;

public class TicketRepositoryImpl implements RFilter<Ticket, TicketFiltro> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Ticket> filterBy(TicketFiltro filter) {
        String qlString = "";

        if (filter.getQuery() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += """
                (
                    lower(t.observacao) like lower(:query) or
                    lower(u.nome) like lower(:query) or
                    lower(u.email) like lower(:query) or
                    lower(e.tombamento) like lower(:query) or
                    lower(e.nome) like lower(:query) or
                    lower(e.descricao) like lower(:query) or
                    lower(e.fabricante) like lower(:query) or
                    lower(e.modelo) like lower(:query)
                )
                """;
        }

        if (filter.getStatus() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "t.status = :status";
        }

        if (filter.getUsuarioId() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "u.id = :usuarioId";
        }

        if (filter.getCategoriaId() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "c.id = :categoriaId";
        }

        if (filter.getDt_reserva() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "t.dt_reserva between :dt_reserva_begin and :dt_reserva_end";
        }

        if (filter.getDt_devolucao() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "t.dt_devolucao between :dt_devolucao_begin and :dt_devolucao_end";
        }

        qlString = """
            select t from Ticket t
            inner join Usuario u
                on t.usuario.id = u.id
            inner join Equipamento e
                on t.equipamento.id = e.id
            inner join Categoria c
                on e.categoria.id = c.id
            """ + (qlString.isBlank() ? "" : "where " + qlString) + """
            order by e.dt_create
            """;

        var query = entityManager.createQuery(qlString);

        if (filter.getQuery() != null) {
            query.setParameter("query", "%" + filter.getQuery() + "%");
        }

        if (filter.getStatus() != null) {
            query.setParameter("status", filter.getStatus());
        }

        if (filter.getUsuarioId() != null) {
            query.setParameter("usuarioId", filter.getUsuarioId());
        }

        if (filter.getCategoriaId() != null) {
            query.setParameter("categoriaId", filter.getCategoriaId());
        }

        if (filter.getDt_reserva() != null) {
            var df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate;

            try {
                parsedDate = df.parse(filter.getDt_reserva());
            } catch (ParseException e) {
                e.printStackTrace();
                parsedDate = new Date();
            }
            Calendar parsedDateBeginningOfDay;
            Calendar parsedDateEndOfDay;
            try {
                parsedDateBeginningOfDay = new GregorianCalendar();
                parsedDateBeginningOfDay.setTime(parsedDate);
                parsedDateBeginningOfDay.set(Calendar.HOUR_OF_DAY, 0);
                parsedDateBeginningOfDay.set(Calendar.MINUTE, 0);
                parsedDateBeginningOfDay.set(Calendar.SECOND, 0);

                parsedDateEndOfDay = new GregorianCalendar();
                parsedDateEndOfDay.setTime(parsedDate);
                parsedDateEndOfDay.set(Calendar.HOUR_OF_DAY, 23);
                parsedDateEndOfDay.set(Calendar.MINUTE, 59);
                parsedDateEndOfDay.set(Calendar.SECOND, 59);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            query.setParameter(
                "dt_reserva_begin",
                parsedDateBeginningOfDay,
                TemporalType.TIMESTAMP
            );
            query.setParameter(
                "dt_reserva_end",
                parsedDateEndOfDay,
                TemporalType.TIMESTAMP
            );
        }

        if (filter.getDt_devolucao() != null) {
            var df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate;

            try {
                parsedDate = df.parse(filter.getDt_devolucao());
            } catch (ParseException e) {
                e.printStackTrace();
                parsedDate = new Date();
            }
            Calendar parsedDateBeginningOfDay;
            Calendar parsedDateEndOfDay;
            try {
                parsedDateBeginningOfDay = new GregorianCalendar();
                parsedDateBeginningOfDay.setTime(parsedDate);
                parsedDateBeginningOfDay.set(Calendar.HOUR_OF_DAY, 0);
                parsedDateBeginningOfDay.set(Calendar.MINUTE, 0);
                parsedDateBeginningOfDay.set(Calendar.SECOND, 0);

                parsedDateEndOfDay = new GregorianCalendar();
                parsedDateEndOfDay.setTime(parsedDate);
                parsedDateEndOfDay.set(Calendar.HOUR_OF_DAY, 23);
                parsedDateEndOfDay.set(Calendar.MINUTE, 59);
                parsedDateEndOfDay.set(Calendar.SECOND, 59);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            query.setParameter(
                "dt_devolucao_begin",
                parsedDateBeginningOfDay,
                TemporalType.TIMESTAMP
            );
            query.setParameter(
                "dt_devolucao_end",
                parsedDateEndOfDay,
                TemporalType.TIMESTAMP
            );
        }

        System.out.println(query.toString());
        return query.getResultList();
    }

}
