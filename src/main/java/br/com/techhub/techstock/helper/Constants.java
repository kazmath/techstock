package br.com.techhub.techstock.helper;

public class Constants {

    public static final String $ROLE_ADMIN = "ROLE_ADMIN";
    public static final String $ROLE_USER = "ROLE_USER";
    public static final String SECURITY_ROLE_ADMIN = "hasAnyRole('" + $ROLE_ADMIN + "')";
    public static final String SECURITY_ROLE_USER = "hasAnyRole('ROLE_ADMIN','" + $ROLE_USER + "')";

    public static final String[] PUBLIC_MATCHERS_GET = { "/", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"};
}
