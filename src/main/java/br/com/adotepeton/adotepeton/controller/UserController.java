package br.com.adotepeton.adotepeton.controller;

import br.com.adotepeton.adotepeton.domain.entity.User;
import br.com.adotepeton.adotepeton.dto.AuthResponse;
import br.com.adotepeton.adotepeton.dto.LoginRequest;
import br.com.adotepeton.adotepeton.dto.RegisterRequest;
import br.com.adotepeton.adotepeton.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest request) {

        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping
    public ResponseEntity<User> Searchbyemail(
            @RequestParam("email") String email) {

        return ResponseEntity.ok(service.Searchbyemail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserId(@PathVariable String email) {
        service.deleteUserEmail(email);
        return ResponseEntity.ok().build();
    }

}
