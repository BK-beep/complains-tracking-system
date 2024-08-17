package ma.attijari.securityservice.service;

import lombok.AllArgsConstructor;
import ma.attijari.securityservice.entities.AppUser;
import ma.attijari.securityservice.repos.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;


    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepo.save(user);
    }

}
