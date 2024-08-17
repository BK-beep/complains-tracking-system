package ma.attijari.securityservice.controllers;

import ma.attijari.securityservice.entities.AppUser;
import ma.attijari.securityservice.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user) {
        AppUser createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Add other user management endpoints as needed
}