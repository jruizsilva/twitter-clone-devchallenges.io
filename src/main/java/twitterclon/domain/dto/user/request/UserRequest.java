package twitterclon.domain.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotBlank
    @Length(min = 4)
    private String username;
    @NotBlank
    @Length(min = 4)
    private String password;
}
