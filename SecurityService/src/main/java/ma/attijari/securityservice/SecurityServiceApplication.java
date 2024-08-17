package ma.attijari.securityservice;

import ma.attijari.securityservice.config.RsaKeyConfig;
import ma.attijari.securityservice.entities.AppRole;
import ma.attijari.securityservice.entities.AppUser;
import ma.attijari.securityservice.repos.AppRoleRepo;
import ma.attijari.securityservice.repos.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableWebSecurity
@EnableConfigurationProperties(RsaKeyConfig.class)
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Autowired
	private AppUserRepo userRepo;

	@Autowired
	private AppRoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Create roles
			Stream.of(
					new AppRole("USER", "Basic user role"),
					new AppRole("ADMIN", "Administrator role")
			).forEach(role -> {
				roleRepo.save(role);
			});

			AppRole userRole = roleRepo.findByRoleName("USER").orElse(null);
			AppRole adminRole = roleRepo.findByRoleName("ADMIN").orElse(null);

			// Create users
			Stream.of(
					new AppUser("user1", "user1", passwordEncoder.encode("1234"), true, List.of(userRole)),
					new AppUser("user2", "user2", passwordEncoder.encode("1234"), true, List.of(userRole)),
					new AppUser("admin", "admin", passwordEncoder.encode("1234"), true, List.of(adminRole, userRole))
			).forEach(user -> {
				userRepo.save(user);
			});
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
