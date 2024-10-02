package br.com.techhub.techstock.controller.filters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketFiltro extends IFilter {
    private String query;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
    private String dt_reserva;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
    private String dt_devolucao;

    private String status;
    private Long   usuarioId;
    private Long   categoriaId;


}
