package br.com.adotepeton.adotepeton.service;

import br.com.adotepeton.adotepeton.domain.entity.User;
import br.com.adotepeton.adotepeton.domain.enums.Role;
import br.com.adotepeton.adotepeton.domain.repository.UserRepository;
import br.com.adotepeton.adotepeton.dto.AuthResponse;
import br.com.adotepeton.adotepeton.dto.LoginRequest;
import br.com.adotepeton.adotepeton.dto.RegisterRequest;
import br.com.adotepeton.adotepeton.exception.ResourceNotFoundException;
import br.com.adotepeton.adotepeton.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        repository.save(user);

        String Bearer = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(Bearer);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user =repository.findByEmail(request.email())
                .orElseThrow();

        String Bearer = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
        return new AuthResponse(Bearer);
    }

    public User Searchbyemail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não localizado " + email)
        );
    }

   public void deleteUserEmail(String email) {
        repository.deleteByEmail(email
        );
   }
}
