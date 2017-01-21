package com.uhp.jwt;

import com.uhp.serviceobject.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    private final List<AntPathRequestMatcher> allow_request_matchers;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        allow_request_matchers = new ArrayList<>();
        allow_request_matchers.add(new AntPathRequestMatcher("/"));
        allow_request_matchers.add(new AntPathRequestMatcher("/ui/**"));
        allow_request_matchers.add(new AntPathRequestMatcher("/api/user/auth"));
        allow_request_matchers.add(new AntPathRequestMatcher("/api/user/refresh-token"));
        allow_request_matchers.add(new AntPathRequestMatcher("/api/user/register"));
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return allow_request_matchers.stream().noneMatch(matcher -> matcher.matches(request));
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
