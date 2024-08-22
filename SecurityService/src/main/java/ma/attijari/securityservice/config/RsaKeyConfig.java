package ma.attijari.securityservice.config;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties(prefix = "rsa")
public record RsaKeyConfig (
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
){
    @Override
    public RSAPublicKey publicKey() {
        return publicKey;
    }

    @Override
    public RSAPrivateKey privateKey() {
        return privateKey;
    }
}
