package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum TicketStatus {
    APROVADO('A', "Aprovado"),
    DESAPROVADO('D', "Desaprovado"),
    PROCESSANDO('P', "Processando");

    @JsonValue
    private char codigo;

    private String descricao;

    TicketStatus(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
