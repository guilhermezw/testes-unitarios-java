package com.spring.login.security;

import com.spring.login.model.UsuarioModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    public String generateToken(Authentication authentication){
        Instant now  = Instant.now();
        long expiry = 3600L;

        UsuarioDetails usuario = (UsuarioDetails) authentication.getPrincipal();

        List<String> role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authoridade -> authoridade.replace("ROLE_", ""))
                .toList();

        var claims = JwtClaimsSet.builder()
                .issuer("auth.login.com")
                .audience(List.of("api.login.com"))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(String.valueOf(usuario.getId()))
                .claim("role", role)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
