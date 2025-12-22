package com.example.GestaoDeBiblioteca.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "chave-super-secreta-com-pelo-menos-32-bytes-:)";

    private static final long EXPIRACAO = 1000 * 60 * 60; // 1 hora

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(UUID id, String email, String nome) {
        return Jwts.builder()
                .setSubject(email)
                .claim("id", id.toString())
                .claim("nome", nome)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(getSigningKey())
                .compact();
    }

    // Valida o token (assinatura + expiração)
    public boolean tokenValido(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extrai as claims do token
    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    public UUID extrairId(String token) {
        String id = extrairClaims(token).get("id", String.class);
        return UUID.fromString(id);
    }


    public String extrairNome(String token) {
        return extrairClaims(token).get("nome", String.class);
    }
}
