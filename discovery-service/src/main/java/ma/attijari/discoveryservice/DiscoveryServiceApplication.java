package ma.attijari.discoveryservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class DiscoveryServiceApplication {
	@Value("${SPRING_CLOUD_CONFIG_URI}")
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServiceApplication.class, args);
		System.out.println("SPRING_CLOUD_CONFIG_URI: "+System.getenv("SPRING_CLOUD_CONFIG_URI"));
	}

}
