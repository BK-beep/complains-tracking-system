package ma.attijari.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class JwtAuthenticationFilter implements GatewayFilter, Ordered {

    @Autowired
    private SecurityServiceClient securityServiceClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("authHeader: " + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            //String token = authHeader.substring(7); //this happens in the security service
            System.out.println("token: " + authHeader);
            return securityServiceClient.validateToken(authHeader)
                    .flatMap(isValid -> {
                        if (isValid) {
                            return chain.filter(exchange); // Token is valid, proceed with the request
                        } else {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete(); // Token is invalid, reject the request
                        }
                    });
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // No token provided, reject the request
        }
    }

    @Override
    public int getOrder() {
        return -100; // Ensure this filter runs before others
    }
}
