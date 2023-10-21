package twitterclon.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitterclon.business.facade.BookFacade;
import twitterclon.domain.dto.book.BookDto;
import twitterclon.domain.dto.book.request.BookRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v2/books")
public class BookController {
    public final BookFacade bookFacade;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        List<BookDto> bookDtoList = bookFacade.findAll();
        return ResponseEntity.ok(bookDtoList);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(
            @PathVariable final Long bookId) {
        BookDto bookDto = bookFacade.findById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable final Long bookId) {
        bookFacade.deleteById(bookId);
        return null;
    }

    @PostMapping
    public ResponseEntity<BookDto> create(
            @Valid
            @RequestBody final BookRequest bookRequest) {
        BookDto bookDto = bookFacade.create(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(bookDto);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> update(@PathVariable final Long bookId,
                                          @RequestBody final BookRequest bookRequest) {
        BookDto bookDto = bookFacade.update(bookId,
                                            bookRequest);
        return ResponseEntity.ok(bookDto);
    }
}
