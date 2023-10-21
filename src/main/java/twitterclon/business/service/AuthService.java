package twitterclon.business.service;

import twitterclon.domain.dto.auth.request.AuthRequest;
import twitterclon.domain.entity.UserEntity;

public interface AuthService {
    UserEntity register(AuthRequest authRequest);
    UserEntity registerAdmin(AuthRequest authRequest);
    String login(AuthRequest authRequest);
}
