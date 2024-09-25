package br.com.techhub.techstock.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.techhub.techstock.model.ProfileType;
import lombok.Getter;

@Getter
public class UserSS implements UserDetails {

    private Long                                   id;
    private String                                 email;
    private String                                 password;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean                                enabled;

    /**
     * @param id
     * @param email
     * @param password
     * @param authorities
     * @param enabled
     */
    public UserSS(
        Long id,
        String email,
        String password,
        List<ProfileType> profileTypes,
        Boolean enabled
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profileTypes.stream()
            .map(t -> new SimpleGrantedAuthority(t.getNome()))
            .collect(Collectors.toList());
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isEnabled() { return enabled; }

    public Boolean hasRole(String nameAuthority) {
        ProfileType profileType = new ProfileType(nameAuthority);

        return getAuthorities().contains(
            new SimpleGrantedAuthority(profileType.getNome())
        );

    }

}
