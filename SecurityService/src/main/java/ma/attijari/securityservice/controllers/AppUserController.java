package ma.attijari.securityservice.controllers;

import ma.attijari.securityservice.entities.AppUser;
import ma.attijari.securityservice.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")

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