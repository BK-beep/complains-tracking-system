package ma.attijari.securityservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/role")
public class RoleController {
    @PostMapping
    public String addRole() {
        return null;
    }
}
