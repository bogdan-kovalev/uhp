package com.uhp.service.impl;

import com.uhp.entity.User;
import com.uhp.exception.InvalidTokenException;
import com.uhp.repository.UserRepository;
import com.uhp.service.UserAuthenticationService;
import com.uhp.serviceobject.AuthenticatedUser;
import com.uhp.serviceobject.AuthenticationToken;
import com.uhp.serviceobject.LoginCredentials;
import com.uhp.util.HashingUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    public static final Long MONTH = TimeUnit.DAYS.toMillis(7);
    private final static String secret = "0O4k3zI4Jud61QF4i1FOOB4G6x9P9hbH";

    @Autowired
    UserRepository userRepository;

    @Override
    public AuthenticationToken auth(LoginCredentials loginCredentials) throws Exception {
        final String email = loginCredentials.email.getValue();
        final User user = userRepository.findByEmail(email);
        validateCredentials(Optional.of(user)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(email + "not found")), loginCredentials);

        return generateToken(createClaims(user));
    }

    @Override
    public AuthenticationToken refreshToken(AuthenticationToken token) throws Exception {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.token)
                .getBody();

        final String userId = claims.getId();
        final String email = claims.getSubject();
        final User user = userRepository.findOne(userId);
        if (user != null && email.equals(user.getEmail())) {
            claims.setExpiration(nextExpirationDate());
            return generateToken(claims);
        } else {
            throw new InvalidTokenException();
        }
    }

    @Override
    public AuthenticatedUser retrieveUser(AuthenticationToken token) throws Exception {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.token)
                .getBody();

        final String userId = claims.getId();
        final String email = claims.getSubject();
        final Date expiration = claims.getExpiration();
        final User user = userRepository.findOne(userId);
        if (user != null && email.equals(user.getEmail()) && expiration.getTime() > System.currentTimeMillis()) {
            claims.setExpiration(nextExpirationDate());
            final AuthenticationToken nextToken = generateToken(claims);
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
            return new AuthenticatedUser(userId, user.getName(), nextToken.token, authorityList);
        } else {
            return null;
        }
    }

    private void validateCredentials(User user, LoginCredentials loginCredentials) throws Exception {
        final String originalHash = user.getPasswordHash();
        final String currentHash = HashingUtil.createHash(loginCredentials.password, user.getPasswordSalt());
        if (!originalHash.equals(currentHash)) {
            throw new Exception("Invalid email or password have been provided");
        }
    }

    private AuthenticationToken generateToken(Claims claims) {
        final String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new AuthenticationToken(token);
    }

    private Claims createClaims(User user) {
        return Jwts.claims()
                .setSubject(user.getEmail())
                .setId(user.getId())
                .setExpiration(nextExpirationDate());
    }

    private Date nextExpirationDate() {
        return new Date(System.currentTimeMillis() + MONTH);
    }
}
