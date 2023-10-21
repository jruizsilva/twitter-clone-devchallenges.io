package twitterclon.business.facade;

import twitterclon.domain.dto.auth.LoginDto;
import twitterclon.domain.dto.auth.RegisterDto;
import twitterclon.domain.dto.auth.request.AuthRequest;

public interface AuthFacade {
    RegisterDto register(AuthRequest authRequest);
    RegisterDto registerAdmin(AuthRequest authRequest);
    LoginDto login(AuthRequest authRequest);
}
