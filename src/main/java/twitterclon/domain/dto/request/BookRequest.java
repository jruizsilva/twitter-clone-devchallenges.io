package twitterclon.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @PastOrPresent
    private LocalDate releaseDate;
    @Positive
    private Integer pages;
    @Positive
    private Double price;
    private Boolean online;
}
