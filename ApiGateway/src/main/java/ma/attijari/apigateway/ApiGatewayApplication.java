package ma.attijari.apigateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
		System.out.println("helloooo");
	}
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		System.out.println("helloooo2");
		RouteLocator routes = builder.routes()
				.route("complaints_search", r -> r.path("/complaints/_search/**")
						.uri("lb://ESSEARCHAPI")) // Load balancer (Eureka) service name
				.route("Eréclamation", r -> r.host("/login/**")
						.uri("http://localhost:4200"))
				/*
				.route("rewrite_route", r -> r.host("*.rewrite.org")
						.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
						.uri("http://httpbin.org"))
				 */
				.build();
		System.out.println("helloooo3");
		return routes;
	}

}
