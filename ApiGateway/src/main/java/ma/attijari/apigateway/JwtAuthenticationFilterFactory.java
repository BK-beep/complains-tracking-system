package ma.attijari.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationFilterFactory.Config> {

    private final JwtAuthenticationGatewayFilter filter;

    public JwtAuthenticationFilterFactory(JwtAuthenticationGatewayFilter filter) {
        super(Config.class);
        this.filter = filter;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return filter;
    }

    public static class Config {
        // If you need any configuration properties, add them here
    }
}