package br.com.techhub.techstock.model.enums;

import lombok.Getter;

@Getter
public enum EquipamentoStatus {
    INDISPONIVEL('I', "Indisponível", "#df3f3f"),
    DISPONIVEL('D', "Disponível", "#6aa554"),
    EM_MANUTENCAO('M', "Em Manutenção", "#e6b128");

    private char codigo;

    private String descricao;
    private String corHex;

    EquipamentoStatus(char codigo, String descricao, String corHex) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.corHex = corHex;
    }
}
