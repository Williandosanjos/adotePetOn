package br.com.adotepeton.adotepeton.security;

import br.com.adotepeton.adotepeton.domain.entity.User;
import br.com.adotepeton.adotepeton.domain.enums.Role;
import br.com.adotepeton.adotepeton.domain.repository.UserRepository;
import br.com.adotepeton.adotepeton.security.dto.AuthResponse;
import br.com.adotepeton.adotepeton.security.dto.LoginRequest;
import br.com.adotepeton.adotepeton.security.dto.RegisterRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

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

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getName()
        );

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = repository.findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getName()
        );

        return new AuthResponse(token);
    }
}
