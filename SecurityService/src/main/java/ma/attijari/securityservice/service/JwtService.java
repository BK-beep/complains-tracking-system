package ma.attijari.securityservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import ma.attijari.securityservice.config.RsaKeyConfig;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;

@Service
@AllArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Autowired
    private RsaKeyConfig rsaKeyConfig;

    // Generate JWT token
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Instant now = Instant.now();
            if (jwt.getExpiresAt().isBefore(now)) {
                return false;
            }
            if (!"self".equals(jwt.getIssuer().toString())) {
                return false;
            }
            return true;
        } catch (JwtException e) {
            return false; // Token is invalid
        }
    }

    // API method to validate token via a REST call
    public ResponseEntity<Boolean> validateHttpToken(String authHeader) {
        String token= null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
             token= authHeader.substring(7);
             if (validateToken(token)) {
                 return ResponseEntity.ok(true);
             }
        }
        return new  ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    }

}
