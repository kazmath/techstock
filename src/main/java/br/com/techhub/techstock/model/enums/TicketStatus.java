package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum TicketStatus {
    APROVADO('S', "Reservado", "#6AA554"),
    DESAPROVADO('N', "Recusado", "#E6B128"),
    PROCESSANDO('P', "Aguardando", "#DF3F3F");

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
