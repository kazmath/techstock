package br.com.techhub.techstock.config;

import br.com.techhub.techstock.repository.UsuarioRepository;
import br.com.techhub.techstock.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return new UserDetailsServiceImpl(usuarioRepository);
    }
}
