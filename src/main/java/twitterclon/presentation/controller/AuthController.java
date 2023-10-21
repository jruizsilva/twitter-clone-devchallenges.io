package twitterclon.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitterclon.business.facade.AuthFacade;
import twitterclon.domain.dto.auth.LoginDto;
import twitterclon.domain.dto.auth.RegisterDto;
import twitterclon.domain.dto.auth.request.AuthRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(@Valid @RequestBody final AuthRequest authRequest) {
        RegisterDto registerDto = authFacade.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(registerDto);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<RegisterDto> registerAdmin(@Valid @RequestBody final AuthRequest authRequest) {
        RegisterDto registerDto = authFacade.registerAdmin(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@Valid @RequestBody final AuthRequest authRequest) {
        LoginDto loginDto = authFacade.login(authRequest);
        return ResponseEntity.ok(loginDto);
    }
}
