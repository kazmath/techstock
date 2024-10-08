package br.com.techhub.techstock.repository.impl;

import java.util.List;

import br.com.techhub.techstock.controller.filters.EquipamentoFiltro;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.repository.RFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EquipamentoRepositoryImpl implements RFilter<Equipamento, EquipamentoFiltro> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Equipamento> filterBy(EquipamentoFiltro filter) {
        String qlString = "";

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += """
                (
                    lower(e.tombamento) like lower(:query) or
                    lower(e.nome) like lower(:query) or
                    lower(e.descricao) like lower(:query) or
                    lower(e.fabricante) like lower(:query) or
                    lower(e.modelo) like lower(:query) or
                    lower(c.nome) like lower(:query)
                )
                """;
        }

        if (filter.getCategoriaId() != null) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "c.id = :categoriaId";
        }

        if (filter.getFabricante() != null && !filter.getFabricante()
            .isBlank()) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "lower(e.fabricante) like lower(:fabricante)";
        }

        if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
            if (!qlString.isBlank()) {
                qlString += " and ";
            }
            qlString += "lower(e.status) = lower(:status)";
        }

        qlString = """
            select e from Equipamento e
            inner join Categoria c
                on e.categoria.id = c.id
            """ + (qlString.isBlank() ? "" : "where " + qlString + " ") + """
            order by e.dtCreate
            """;

        var query = entityManager.createQuery(qlString);

        if (filter.getQuery() != null && !filter.getQuery().isBlank()) {
            query.setParameter("query", "%" + filter.getQuery() + "%");
        }

        if (filter.getCategoriaId() != null) {
            query.setParameter("categoriaId", filter.getCategoriaId());
        }

        if (filter.getFabricante() != null && !filter.getFabricante()
            .isBlank()) {
            query.setParameter(
                "fabricante",
                "%" + filter.getFabricante() + "%"
            );
        }

        if (filter.getStatus() != null && !filter.getStatus().isBlank()) {
            query.setParameter("status", filter.getStatus());
        }

        return query.getResultList();
    }

}
