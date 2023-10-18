package twitterclon.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import twitterclon.domain.entity.BookEntity;
import twitterclon.persistence.IBookRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Validated
@Tag(name = "BookController",
     description = "BookController management APIs")
public class BookControllerCourse {
    private final IBookRepository iBookRepository;
    private final Logger log = Logger.getLogger(BookControllerCourse.class.getName());

    @GetMapping
    public ResponseEntity<List<BookEntity>> findAll() {
        List<BookEntity> bookEntityList = iBookRepository.findAll();
        return ResponseEntity.ok(bookEntityList);
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "Busca un libro por su id",
               description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
               tags = {"tutorials", "get"})
    @ApiResponse(responseCode = "200",
                 content = {@Content(schema = @Schema(implementation = BookEntity.class),
                                     mediaType = "application/json")})
    @ApiResponse(responseCode = "404",
                 content = {@Content(schema = @Schema())})
    public ResponseEntity<BookEntity> findById(@Parameter(
            description = "Id del libro a buscar"
    ) @PathVariable final Long bookId) {
        Optional<BookEntity> bookEntityOptional = iBookRepository.findById(bookId);
        return ResponseEntity.of(bookEntityOptional);
    }

    @PostMapping
    public ResponseEntity<BookEntity> create(@Valid @RequestBody final BookEntity bookEntity) {
        if (bookEntity.getId() != null) {
            log.warning("Trying to create a book with id");
            return ResponseEntity.badRequest()
                                 .build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(iBookRepository.save(bookEntity));
    }

    @PutMapping
    public ResponseEntity<BookEntity> update(@Valid @RequestBody final BookEntity bookEntity) {
        if (bookEntity.getId() == null) {
            log.warning("Trying to update a non existent book");
            return ResponseEntity.badRequest()
                                 .build();
        }
        if (!iBookRepository.existsById(bookEntity.getId())) {
            log.warning("Trying to update a non existent book");
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok(iBookRepository.save(bookEntity));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long bookId) {
        if (!iBookRepository.existsById(bookId)) {
            log.warning("Trying to delete a non existent book");
            return ResponseEntity.notFound()
                                 .build();
        }
        iBookRepository.deleteById(bookId);
        return ResponseEntity.noContent()
                             .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        log.info("Removing all books");
        iBookRepository.deleteAll();
        return ResponseEntity.noContent()
                             .build();
    }
}
