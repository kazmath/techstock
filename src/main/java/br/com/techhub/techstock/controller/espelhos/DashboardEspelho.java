package br.com.techhub.techstock.controller.espelhos;

public record DashboardEspelho(
    int ticketsTotal,
    int ticketsAbertosEsseMes,
    int equipamentosTotal,
    int equipamentosConserto,
    int equipamentosEmUso
) implements IEspelho {}
