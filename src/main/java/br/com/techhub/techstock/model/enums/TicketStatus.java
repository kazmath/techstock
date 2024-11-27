package br.com.techhub.techstock.model.enums;

import lombok.Getter;

@Getter
public enum TicketStatus {
    RESERVADO('S', "Reservado", "#6AA554"),
    RECUSADO('N', "Recusado", "#DF3F3F"),
    AGUARDANDO('P', "Aguardando", "#E6B128"),
    FINALIZADO('F', "Finalizado", "#666666");

    private char codigo;

    private String descricao;
    private String corHex;

    TicketStatus(char codigo, String descricao, String corHex) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.corHex = corHex;
    }
}
