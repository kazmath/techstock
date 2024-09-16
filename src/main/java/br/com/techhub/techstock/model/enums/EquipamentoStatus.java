package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum EquipamentoStatus {
    INDISPONIVEL('I', "Indisponível", "#6AA554"),
    DISPONIVEL('D', "Disponível", "#E6B128"),
    EM_MANUTENCAO('M', "Em Manutenção", "#DF3F3F");

    @JsonValue
    private char codigo;

    private String descricao;
    private String corHex;

    EquipamentoStatus(char codigo, String descricao, String corHex) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.corHex = corHex;
    }
}
