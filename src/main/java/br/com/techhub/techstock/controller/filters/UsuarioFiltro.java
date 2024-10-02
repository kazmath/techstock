package br.com.techhub.techstock.controller.filters;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioFiltro extends IFilter {
    private String query;
}
