package twitterclon.domain.dto.book;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private Integer pages;
    private Double price;
    private Boolean online;
}
