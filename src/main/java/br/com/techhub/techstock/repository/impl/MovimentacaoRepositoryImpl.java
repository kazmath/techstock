package br.com.techhub.techstock.repository.impl;

import java.util.List;

import br.com.techhub.techstock.controller.filters.MovimentacaoFiltro;
import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.repository.RFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class MovimentacaoRepositoryImpl implements RFilter<Movimentacao, MovimentacaoFiltro> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Movimentacao> filterBy(MovimentacaoFiltro filter) {
        String qlString = "";

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += """
                (
                    lower(m.usuario.nome) like lower(:query) or
                    lower(m.usuario.email) like lower(:query) or
                    lower(m.usuarioAdm.nome) like lower(:query) or
                    lower(m.usuarioAdm.email) like lower(:query) or
                    lower(t.observacao) like lower(:query) or
                    lower(e.tombamento) like lower(:query) or
                    lower(e.nome) like lower(:query) or
                    lower(e.descricao) like lower(:query) or
                    lower(e.fabricante) like lower(:query) or
                    lower(e.modelo) like lower(:query)
                )
                """;
        }

        if (filter.getUsuarioId() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "u.id = :usuarioId";
        }

        if (filter.getUsuarioAdmId() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "u.id = :usuarioAdmId";
        }

        // inner join Usuario u
        //     on m.usuario.id = u.id
        // inner join Usuario ua
        //     on m.usuarioAdm.id = u.id
        qlString = """
            select m from Movimentacao m
            inner join Ticket t
                on m.ticket.id = t.id
            inner join Equipamento e
                on t.equipamento.id = e.id
            """ + (qlString.isBlank() ? "" : "where " + qlString + " ") + """
            order by e.dtCreate
            """;

        var query = entityManager.createQuery(qlString);

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            query.setParameter("query", "%" + filter.getQuery() + "%");
        }

        if (filter.getUsuarioId() != null) {
            query.setParameter("usuarioId", filter.getUsuarioId());
        }

        if (filter.getUsuarioAdmId() != null) {
            query.setParameter("usuarioAdmId", filter.getUsuarioAdmId());
        }

        return query.getResultList();
    }

}
