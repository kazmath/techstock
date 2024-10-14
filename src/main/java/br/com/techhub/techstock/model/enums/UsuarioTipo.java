package br.com.techhub.techstock.model.enums;

public enum UsuarioTipo {
    ADMIN("admin"), USER("user");

    private String role;

    UsuarioTipo(String role) {
        this.role = role;
    }

    public String getRole() { return role; }
}
