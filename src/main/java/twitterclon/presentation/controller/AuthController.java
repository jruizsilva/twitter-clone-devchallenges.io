package twitterclon.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitterclon.domain.dto.AuthResponseDto;
import twitterclon.domain.dto.LoginUserDto;
import twitterclon.domain.dto.RegisterUserDto;
import twitterclon.domain.entity.RoleEntity;
import twitterclon.domain.entity.UserEntity;
import twitterclon.persistence.RoleRepository;
import twitterclon.persistence.UserRepository;
import twitterclon.security.JwtGenerator;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody final RegisterUserDto registerUserDto) {
        if (userRepository.existsByUsernameIgnoreCase(registerUserDto.getUsername())) {
            return ResponseEntity.badRequest()
                                 .body("Username already exists");
        }
        UserEntity userEntity =
                UserEntity.builder()
                          .username(registerUserDto.getUsername())
                          .password(passwordEncoder.encode(registerUserDto.getPassword()))
                          .build();
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByNameIgnoreCase("USER");
        if (roleEntityOptional.isEmpty()) {
            return ResponseEntity.badRequest()
                                 .body("Role USER not found");
        }
        RoleEntity roleEntity = roleEntityOptional.get();
        userEntity.setRoles(Collections.singletonList(roleEntity));
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("User created successfully");
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody final RegisterUserDto registerUserDto) {
        if (userRepository.existsByUsernameIgnoreCase(registerUserDto.getUsername())) {
            return ResponseEntity.badRequest()
                                 .body("Username already exists");
        }
        UserEntity userEntity =
                UserEntity.builder()
                          .username(registerUserDto.getUsername())
                          .password(passwordEncoder.encode(registerUserDto.getPassword()))
                          .build();
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByNameIgnoreCase("ADMIN");
        if (roleEntityOptional.isEmpty()) {
            return ResponseEntity.badRequest()
                                 .body("Role ADMIN not found");
        }
        RoleEntity roleEntity = roleEntityOptional.get();
        userEntity.setRoles(Collections.singletonList(roleEntity));
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody final LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(),
                                                        loginUserDto.getPassword()));
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
