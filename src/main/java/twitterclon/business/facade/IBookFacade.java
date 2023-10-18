package twitterclon.business.facade;

import twitterclon.domain.dto.BookDto;
import twitterclon.domain.dto.request.BookRequest;

import java.util.List;

public interface IBookFacade {
    List<BookDto> findAll();
    BookDto findById(Long id);
    BookDto create(BookRequest bookRequest);
    BookDto update(Long id, BookRequest bookRequest);
    void deleteById(Long id);
}
