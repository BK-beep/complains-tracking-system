package ma.attijari.apigateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
@EnableDiscoveryClient
@Configuration
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
		//System.out.println("helloooo");
	}
	@Autowired
	private JwtAuthenticationGatewayFilter jwtAuthenticationGatewayFilter;


	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		System.out.println("helloooo");
		return builder.routes()
				.route("SecurityService", r  -> r
						.path("SecurityService/**")
						//.filters(f -> f.filter(jwtAuthenticationFilter).stripPrefix(1))
						.filters(f -> f.stripPrefix(1))
						.uri("lb://SecurityService"))
				.route("ESSearchAPI", r  -> r
						.path("ESSearchAPI/**")
						.filters(f -> f.filter(jwtAuthenticationGatewayFilter).stripPrefix(1))
						.uri("lb://ESSearchAPI"))
				.build();

		 }
	@Bean
	@Primary
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}

}
