package br.com.techhub.techstock.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    private final JWTUtils           jwtUtils;
    private final UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(
        AuthenticationManager authenticationManager,
        JWTUtils jwtUtils,
        UserDetailsService userDetailsService
    ) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(
                header.substring(7)
            );

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(
        String token
    ) {
        if (jwtUtils.tokenValid(token)) {
            UserDetails user = userDetailsService.loadUserByUsername(
                jwtUtils.getUsername(token)
            );

            return new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
            );
        }

        return null;
    }
}
