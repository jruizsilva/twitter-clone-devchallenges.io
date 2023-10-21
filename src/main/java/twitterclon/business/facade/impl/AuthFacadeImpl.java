package twitterclon.business.facade.impl;

import lombok.RequiredArgsConstructor;
import twitterclon.business.facade.AuthFacade;
import twitterclon.business.mapper.auth.RegisterMapper;
import twitterclon.business.service.AuthService;
import twitterclon.domain.dto.auth.LoginDto;
import twitterclon.domain.dto.auth.RegisterDto;
import twitterclon.domain.dto.auth.request.AuthRequest;
import twitterclon.domain.entity.UserEntity;

@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;
    private final RegisterMapper registerMapper;

    @Override
    public RegisterDto register(AuthRequest authRequest) {
        UserEntity userEntityCreated = authService.register(authRequest);
        return registerMapper.toRegisterDto(userEntityCreated);
    }

    @Override
    public RegisterDto registerAdmin(AuthRequest authRequest) {
        UserEntity userEntityCreated = authService.registerAdmin(authRequest);
        return registerMapper.toRegisterDto(userEntityCreated);
    }

    @Override
    public LoginDto login(AuthRequest authRequest) {
        String token = authService.login(authRequest);
        return new LoginDto(token);
    }
}
