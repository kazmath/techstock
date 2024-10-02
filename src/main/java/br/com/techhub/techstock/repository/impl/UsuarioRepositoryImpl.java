package br.com.techhub.techstock.repository.impl;

import java.util.List;

import br.com.techhub.techstock.controller.filters.UsuarioFiltro;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.RFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UsuarioRepositoryImpl implements RFilter<Usuario, UsuarioFiltro> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> filterBy(UsuarioFiltro filter) {
        String qlString = "";

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += """
                (
                    lower(u.nome) like lower(:query) or
                    lower(u.email) like lower(:query) or
                    lower(u.codigo) like lower(:query)
                )
                """;
        }

        qlString = """
            select u from Usuario u
            """ + (qlString.isBlank() ? "" : "where " + qlString + " ") + """
            order by u.dtCreate
            """;

        var query = entityManager.createQuery(qlString);

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            query.setParameter("query", "%" + filter.getQuery() + "%");
        }

        return query.getResultList();
    }

}
