package com.viniciusdev.project_performance.features.auth;

import com.viniciusdev.project_performance.features.auth.dtos.LoginRequest;
import com.viniciusdev.project_performance.features.auth.dtos.LoginResponse;
import com.viniciusdev.project_performance.features.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        var optUser = userRepository.findByEmail(request.email());

        if (optUser.isEmpty() || !optUser.get().isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("Email or password is invalid");
        }

        Instant now = Instant.now();
        Long expiresIn = 300L;

        List<String> scope = optUser.get()
                .getRoles()
                .stream()
                .map(role -> role.getName())
                .toList();

        var claims = JwtClaimsSet.builder()
                .issuer("project_performance_backend")
                .subject(optUser.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok().body(new LoginResponse(jwtValue));

    }

}
