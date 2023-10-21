package twitterclon.business.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import twitterclon.business.facade.AuthFacade;
import twitterclon.business.service.AuthService;
import twitterclon.domain.dto.auth.LoginDto;
import twitterclon.domain.dto.auth.RegisterDto;
import twitterclon.domain.dto.auth.request.AuthRequest;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;

    @Override
    public RegisterDto register(AuthRequest authRequest) {
        authService.register(authRequest);
        return new RegisterDto("User created successfully");
    }

    @Override
    public RegisterDto registerAdmin(AuthRequest authRequest) {
        authService.registerAdmin(authRequest);
        return new RegisterDto("User created successfully");
    }

    @Override
    public LoginDto login(AuthRequest authRequest) {
        String token = authService.login(authRequest);
        return new LoginDto(token);
    }
}
