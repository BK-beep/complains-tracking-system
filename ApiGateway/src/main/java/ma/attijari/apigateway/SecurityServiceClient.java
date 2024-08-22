package ma.attijari.apigateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class SecurityServiceClient {
    private final WebClient webClient;

    public SecurityServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:5432").build();
    }


    public Mono<Boolean> validateToken(String token) {
        return webClient.post()
                .uri("/api/auth/validate-token")  // Adjust this URI to match your security service endpoint
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                        return Mono.just(false);
                    }
                    return Mono.error(ex);
                });
    }
}