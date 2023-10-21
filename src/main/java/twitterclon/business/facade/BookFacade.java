package twitterclon.business.facade;

import twitterclon.domain.dto.book.BookDto;
import twitterclon.domain.dto.book.request.BookRequest;

import java.util.List;

public interface BookFacade {
    List<BookDto> findAll();
    BookDto findById(Long id);
    BookDto create(BookRequest bookRequest);
    BookDto update(Long id, BookRequest bookRequest);
    void deleteById(Long id);
}
