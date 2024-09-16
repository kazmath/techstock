package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum TicketStatus {
    APROVADO('A', "Aprovado", "#6AA554"),
    DESAPROVADO('D', "Desaprovado", "#E6B128"),
    PROCESSANDO('P', "Processando", "#DF3F3F");

    @JsonValue
    private char codigo;

    private String descricao;
    private String corHex;

    TicketStatus(char codigo, String descricao, String corHex) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.corHex = corHex;
    }
}
