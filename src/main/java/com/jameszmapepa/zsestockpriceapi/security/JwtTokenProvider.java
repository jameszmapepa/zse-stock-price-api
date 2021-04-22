package com.jameszmapepa.zsestockpriceapi.security;


import com.jameszmapepa.zsestockpriceapi.model.Partner;
import com.jameszmapepa.zsestockpriceapi.partner.PartnerService;
import com.jameszmapepa.zsestockpriceapi.security.payload.LoginRequest;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.jwtsecret}")
    private String jwtSecret;
    @Value("${jwt.jwtexpirationinms}")
    private int jwtExpirationInMs;
    private final PartnerService partnerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public String generateToken(LoginRequest loginRequest) {
        log.info("Generating  Token Request.... {} | {}", loginRequest.getUsername(), loginRequest.getPassword());
        final Optional<Partner> optionalPartner = partnerService.findByPartnerId(loginRequest.getUsername());
        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            log.info("Partner Found.... {} | {}", partner.getPartnerKey(), loginRequest.getPassword());
        }
        return this.tokenBuilder(loginRequest.getUsername());
    }


    private String tokenBuilder(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Authentication getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}