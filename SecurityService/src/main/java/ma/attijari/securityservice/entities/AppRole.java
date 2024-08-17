package ma.attijari.securityservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column(unique=true)
    private String roleName;
    private String roleDescription;

    public AppRole(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
}
