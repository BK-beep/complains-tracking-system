package ma.attijari.securityservice.controllers;

import ma.attijari.securityservice.dtos.LoginRequest;
import ma.attijari.securityservice.entities.AppUser;
import ma.attijari.securityservice.service.AppUserService;
import ma.attijari.securityservice.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    AppUserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }
    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user) {
        System.out.println("registering user");
        AppUser createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        Map<String, String> token = new HashMap<>();
        token.put("token", jwtService.generateToken(authentication));
        return ResponseEntity.ok(token);
    }

    //validate token endpoint
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return this.jwtService.validateHttpToken(authHeader);
    }
}