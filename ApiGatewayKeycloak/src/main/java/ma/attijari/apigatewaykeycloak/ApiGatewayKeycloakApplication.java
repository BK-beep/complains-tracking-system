package ma.attijari.apigatewaykeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayKeycloakApplication.class, args);
	}


}
