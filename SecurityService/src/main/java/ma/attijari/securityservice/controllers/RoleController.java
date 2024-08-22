package ma.attijari.securityservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RoleController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    @PostMapping
    public String addRole() {
        return null;
    }
}
