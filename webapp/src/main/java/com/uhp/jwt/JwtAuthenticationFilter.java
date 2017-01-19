package com.uhp.jwt;

import com.uhp.serviceobject.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final List<String> skipJwtAuth;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        skipJwtAuth = new ArrayList<>();
        skipJwtAuth.add("/api/login");
        skipJwtAuth.add("/api/register");
        skipJwtAuth.add("/error");
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return !skipJwtAuth.contains(request.getServletPath());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) authResult.getPrincipal();
        response.setHeader("Authentication", "Bearer " + authenticatedUser.getToken());
        chain.doFilter(request, response);
    }
}
