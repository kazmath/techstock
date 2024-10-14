package br.com.techhub.techstock.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.techhub.techstock.model.enums.UsuarioTipo;

public class UserSS implements UserDetails {

    private Long        id;
    private String      email;
    private String      password;
    private UsuarioTipo usuarioTipo;

    public UserSS(
        Long id,
        String email,
        String password,
        UsuarioTipo usuarioTipo
    ) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.usuarioTipo = usuarioTipo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.usuarioTipo == UsuarioTipo.ADMIN) return List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
        );
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Long getId() { return id; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}
