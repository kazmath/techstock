package br.com.techhub.techstock.repository.impl;

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

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
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

        if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
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

        if ((filter.getDt_reserva_begin() != null && !filter
            .getDt_reserva_begin()
            .isBlank()) || (filter.getDt_reserva_end() != null && !filter
                .getDt_reserva_end()
                .isBlank())) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "t.dt_reserva between :dt_reserva_begin and :dt_reserva_end";
        }

        if ((filter.getDt_devolucao_begin() != null && !filter
            .getDt_devolucao_begin()
            .isBlank()) || (filter.getDt_devolucao_end() != null && !filter
                .getDt_devolucao_end()
                .isBlank())) {
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
            """ + (qlString.isBlank() ? "" : "where " + qlString + " ") + """
            order by e.dtCreate
            """;

        var query = entityManager.createQuery(qlString);

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            query.setParameter("query", "%" + filter.getQuery() + "%");
        }

        if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
            query.setParameter("status", filter.getStatus());
        }

        if (filter.getUsuarioId() != null) {
            query.setParameter("usuarioId", filter.getUsuarioId());
        }

        if (filter.getCategoriaId() != null) {
            query.setParameter("categoriaId", filter.getCategoriaId());
        }

        if ((filter.getDt_reserva_begin() != null && !filter
            .getDt_reserva_begin()
            .isBlank()) || (filter.getDt_reserva_end() != null && !filter
                .getDt_reserva_end()
                .isBlank())) {
            var df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDateBegin;
            Date parsedDateEnd;
            Calendar parsedDateBeginningOfDay = new GregorianCalendar();
            Calendar parsedDateEndOfDay = new GregorianCalendar();

            try {
                if (!(filter.getDt_reserva_begin() != null)) {
                    throw new Exception();
                }
                parsedDateBegin = df.parse(filter.getDt_reserva_begin());
                parsedDateBeginningOfDay.setTime(parsedDateBegin);
            } catch (Exception e) {
                parsedDateBegin = new Date();
                parsedDateBeginningOfDay.setTime(parsedDateBegin);
                parsedDateBeginningOfDay.set(Calendar.DAY_OF_MONTH, 1);
            }
            try {
                if (!(filter.getDt_reserva_end() != null)) {
                    throw new Exception();
                }
                parsedDateEnd = df.parse(filter.getDt_reserva_end());
                if (!parsedDateBegin.before(parsedDateEnd))
                    throw new Exception();
                parsedDateEndOfDay.setTime(parsedDateEnd);
            } catch (Exception e) {
                parsedDateEnd = parsedDateBegin;
                parsedDateEndOfDay.setTime(parsedDateEnd);
                parsedDateEndOfDay.set(
                    Calendar.DAY_OF_MONTH,
                    parsedDateEndOfDay.getActualMaximum(Calendar.DAY_OF_MONTH)
                );
            }
            try {
                parsedDateBeginningOfDay.set(Calendar.HOUR_OF_DAY, 0);
                parsedDateBeginningOfDay.set(Calendar.MINUTE, 0);
                parsedDateBeginningOfDay.set(Calendar.SECOND, 0);

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

        if (filter.getDt_devolucao_begin() != null && !filter
            .getDt_devolucao_begin()
            .isBlank() || filter.getDt_devolucao_end() != null && !filter
                .getDt_devolucao_end()
                .isBlank()) {
            var df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDateBegin;
            Date parsedDateEnd;
            Calendar parsedDateBeginningOfDay = new GregorianCalendar();
            Calendar parsedDateEndOfDay = new GregorianCalendar();

            try {
                if (!(filter.getDt_devolucao_begin() != null)) {
                    throw new Exception();
                }
                parsedDateBegin = df.parse(filter.getDt_devolucao_begin());
                parsedDateBeginningOfDay.setTime(parsedDateBegin);
            } catch (Exception e) {
                parsedDateBegin = new Date();
                parsedDateBeginningOfDay.setTime(parsedDateBegin);
                parsedDateBeginningOfDay.set(Calendar.DAY_OF_MONTH, 1);
            }
            try {
                if (!(filter.getDt_devolucao_end() != null)) {
                    throw new Exception();
                }
                parsedDateEnd = df.parse(filter.getDt_devolucao_end());
                if (!(parsedDateBegin.before(parsedDateEnd))) {
                    throw new Exception();
                }
                parsedDateEndOfDay.setTime(parsedDateEnd);
            } catch (Exception e) {
                parsedDateEnd = parsedDateBegin;
                parsedDateEndOfDay.setTime(parsedDateEnd);
                parsedDateEndOfDay.set(
                    Calendar.DAY_OF_MONTH,
                    parsedDateEndOfDay.getActualMaximum(Calendar.DAY_OF_MONTH)
                );
            }
            try {
                parsedDateBeginningOfDay.set(Calendar.HOUR_OF_DAY, 0);
                parsedDateBeginningOfDay.set(Calendar.MINUTE, 0);
                parsedDateBeginningOfDay.set(Calendar.SECOND, 0);

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

        // if (filter.getDt_devolucao() != null && !filter.getDt_devolucao()
        //     .isBlank()) {
        //     var df = new SimpleDateFormat("yyyy-MM-dd");
        //     Date parsedDate;

        //     try {
        //         parsedDate = df.parse(filter.getDt_devolucao());
        //     } catch (ParseException e) {
        //         e.printStackTrace();
        //         parsedDate = new Date();
        //     }
        //     Calendar parsedDateBeginningOfDay;
        //     Calendar parsedDateEndOfDay;
        //     try {
        //         parsedDateBeginningOfDay = new GregorianCalendar();
        //         parsedDateBeginningOfDay.setTime(parsedDate);
        //         parsedDateBeginningOfDay.set(Calendar.HOUR_OF_DAY, 0);
        //         parsedDateBeginningOfDay.set(Calendar.MINUTE, 0);
        //         parsedDateBeginningOfDay.set(Calendar.SECOND, 0);

        //         parsedDateEndOfDay = new GregorianCalendar();
        //         parsedDateEndOfDay.setTime(parsedDate);
        //         parsedDateEndOfDay.set(Calendar.HOUR_OF_DAY, 23);
        //         parsedDateEndOfDay.set(Calendar.MINUTE, 59);
        //         parsedDateEndOfDay.set(Calendar.SECOND, 59);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //         throw e;
        //     }

        //     query.setParameter(
        //         "dt_devolucao_begin",
        //         parsedDateBeginningOfDay,
        //         TemporalType.TIMESTAMP
        //     );
        //     query.setParameter(
        //         "dt_devolucao_end",
        //         parsedDateEndOfDay,
        //         TemporalType.TIMESTAMP
        //     );
        // }

        System.out.println(query.toString());
        return query.getResultList();
    }

}
