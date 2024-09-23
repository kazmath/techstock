package br.com.techhub.techstock.controller.filters;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovimentacaoFiltro extends IFilter {
    private Long usuarioId;
}
