package br.com.techhub.techstock.security;

import br.com.techhub.techstock.model.enums.UsuarioPerfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserSS implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private UsuarioPerfil usuarioPerfil;

    public UserSS(Long id, String email, String password, UsuarioPerfil usuarioPerfil) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.usuarioPerfil = usuarioPerfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.usuarioPerfil == UsuarioPerfil.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
