package twitterclon.business.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twitterclon.business.service.AuthService;
import twitterclon.common.exception.AuthException;
import twitterclon.common.util.constants.AuthConstants;
import twitterclon.domain.dto.auth.request.AuthRequest;
import twitterclon.domain.entity.RoleEntity;
import twitterclon.domain.entity.UserEntity;
import twitterclon.persistence.RoleRepository;
import twitterclon.persistence.UserRepository;
import twitterclon.security.jwt.JwtGenerator;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public UserEntity register(AuthRequest authRequest) {
        if (userRepository.existsByUsernameIgnoreCase(authRequest.getUsername())) {
            throw new AuthException(HttpStatus.CONFLICT,
                                    String.format(AuthConstants.USER_ALREADY_EXISTS_MESSAGE_ERROR,
                                                  authRequest.getUsername()));
        }
        UserEntity userEntity =
                UserEntity.builder()
                          .username(authRequest.getUsername())
                          .password(passwordEncoder.encode(authRequest.getPassword()))
                          .build();
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByNameIgnoreCase("USER");
        if (roleEntityOptional.isEmpty()) {
            throw new AuthException(HttpStatus.NOT_FOUND,
                                    String.format(AuthConstants.ROLE_NOT_FOUND_MESSAGE_ERROR,
                                                  "USER"));
        }
        RoleEntity roleEntity = roleEntityOptional.get();
        userEntity.setRoles(Collections.singletonList(roleEntity));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity registerAdmin(AuthRequest authRequest) {
        if (userRepository.existsByUsernameIgnoreCase(authRequest.getUsername())) {
            throw new AuthException(HttpStatus.CONFLICT,
                                    String.format(AuthConstants.USER_ALREADY_EXISTS_MESSAGE_ERROR,
                                                  authRequest.getUsername()));
        }
        UserEntity userEntity =
                UserEntity.builder()
                          .username(authRequest.getUsername())
                          .password(passwordEncoder.encode(authRequest.getPassword()))
                          .build();
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByNameIgnoreCase("ADMIN");
        if (roleEntityOptional.isEmpty()) {
            throw new AuthException(HttpStatus.NOT_FOUND,
                                    String.format(AuthConstants.ROLE_NOT_FOUND_MESSAGE_ERROR,
                                                  "ADMIN"));
        }
        RoleEntity roleEntity = roleEntityOptional.get();
        userEntity.setRoles(Collections.singletonList(roleEntity));
        return userRepository.save(userEntity);
    }

    @Override
    public String login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                                                        authRequest.getPassword()));
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        return jwtGenerator.generateToken(authentication);
    }
}
