package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum MovimentacaoTipo {
    ENTRADA('E', "Entrada"),
    SAIDA('S', "Saida"),
    TRANSFERENCIA('T', "Transferencia");

    @JsonValue
    private char codigo;

    private String descricao;

    MovimentacaoTipo(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
