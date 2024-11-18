package br.com.techhub.techstock.controller.espelhos;

import java.util.List;

public record AuthEspelho(
    String token,
    String email,
    String codigo,
    List<String> permissions
) implements IEspelho {}
