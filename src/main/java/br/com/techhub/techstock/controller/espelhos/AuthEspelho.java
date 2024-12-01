package br.com.techhub.techstock.controller.espelhos;

import java.util.List;

public record AuthEspelho(
    String token,
    String nome,
    String email,
    String codigo,
    List<String> permissions,
    Long userId
) implements IEspelho {}
