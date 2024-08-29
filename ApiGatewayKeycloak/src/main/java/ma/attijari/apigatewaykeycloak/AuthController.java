package ma.attijari.apigatewaykeycloak;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@Data
public class AuthController {

    @GetMapping("/api/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
