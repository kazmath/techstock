package br.com.techhub.techstock.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum EquipamentoStatus {
    INDISPONIVEL('I', "Indisponível"),
    DISPONIVEL('D', "Disponível"),
    EM_MANUTENCAO('M', "Em Manutenção");

    @JsonValue
    private char codigo;

    private String descricao;

    EquipamentoStatus(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
