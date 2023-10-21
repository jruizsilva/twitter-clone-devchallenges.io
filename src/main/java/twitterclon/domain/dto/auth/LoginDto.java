package twitterclon.domain.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String accessToken;
    private String tokenType;

    public LoginDto(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer ";
    }
}
