package twitterclon.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
@Valid
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Positive
    private Integer pages;
    @Positive
    private Double price;
    private Boolean online;

}