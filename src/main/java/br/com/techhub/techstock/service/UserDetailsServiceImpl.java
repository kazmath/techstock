package br.com.techhub.techstock.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.repository.UsuarioRepository;
import br.com.techhub.techstock.security.UserSS;

// TODO: Tentar passar para usuarioService
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
        String email
    ) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSS(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getUsuarioTipo()
        );
    }

}
