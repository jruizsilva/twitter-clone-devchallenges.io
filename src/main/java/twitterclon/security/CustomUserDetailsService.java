package twitterclon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import twitterclon.domain.entity.RoleEntity;
import twitterclon.domain.entity.UserEntity;
import twitterclon.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<GrantedAuthority> mapToAuthorities(List<RoleEntity> roleEntityList) {
        List<GrantedAuthority> list = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntityList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleEntity.getName());
            list.add(simpleGrantedAuthority);
        }
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        Optional<UserEntity> userEntityOptional = userRepository.findByUsernameIgnoreCase(username);
        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        userEntity = userEntityOptional.get();
        return new User(userEntity.getUsername(),
                        userEntity.getPassword(),
                        mapToAuthorities(userEntity.getRoles()));
    }
}
