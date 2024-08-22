package ma.attijari.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private SecurityServiceClient securityServiceClient;

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(securityServiceClient.validateToken(authHeader).block());
    }
}
