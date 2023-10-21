package twitterclon.domain.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank
    @Length(min = 4)
    private String username;
    @NotBlank
    @Length(min = 4)
    private String password;
}
