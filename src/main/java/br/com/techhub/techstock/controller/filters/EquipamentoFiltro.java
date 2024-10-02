package br.com.techhub.techstock.controller.filters;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EquipamentoFiltro extends IFilter {
    private String query;

    private Long   categoriaId;
    private String fabricante;
    private String status;
}
