package twitterclon.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto {
    @NotBlank
    @Length(min = 4)
    private String username;
    @NotBlank
    @Length(min = 4)
    private String password;

}
