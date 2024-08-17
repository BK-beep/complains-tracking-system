package ma.attijari.securityservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(unique=true)
    private String username;
    private String password;
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;

}
