package ma.attijari.securityservice.repos;

import ma.attijari.securityservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepo extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRoleName(String roleName);
}
