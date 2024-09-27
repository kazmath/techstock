package br.com.techhub.techstock.model.enums;

public enum UsuarioPerfil {
    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioPerfil(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
