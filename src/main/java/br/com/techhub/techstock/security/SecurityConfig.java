package br.com.techhub.techstock.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Value("${SWAGGER_ACCESS}")
    private boolean can_access_swagger;

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(
                session -> session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )
            .authorizeHttpRequests(
                authorize -> authorize //
                    .requestMatchers(
                        HttpMethod.GET,
                        "/swagger-ui/**",
                        "/api-docs/**"
                    )
                    .access(
                        (a, o) -> new AuthorizationDecision(can_access_swagger)
                    )

                    .requestMatchers(HttpMethod.GET, "/techstock/**")
                    .permitAll()

                    .requestMatchers(HttpMethod.GET, "/assets/**")
                    .permitAll()

                    .requestMatchers(HttpMethod.GET, "/")
                    .permitAll()

                    .requestMatchers(HttpMethod.POST, "/api/usuario/login")
                    .permitAll()


                    // TODO: Rever permissões
                    // .requestMatchers("/api/**")
                    // .permitAll()

                    .anyRequest()
                    .authenticated()
            )
            .addFilterBefore(
                securityFilter,
                UsernamePasswordAuthenticationFilter.class
            )
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration()
            .applyPermitDefaultValues();
        configuration.setAllowedMethods(
            Arrays.asList(
                "POST",
                "GET",
                "PUT",
                "DELETE",
                "OPTIONS",
                "PATCH",
                "HEAD"
            )
        );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
