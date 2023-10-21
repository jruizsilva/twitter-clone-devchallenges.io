package twitterclon.business.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import twitterclon.business.facade.BookFacade;
import twitterclon.business.mapper.book.IBookDtoMapper;
import twitterclon.business.mapper.book.IBookRequestMapper;
import twitterclon.business.service.BookService;
import twitterclon.domain.dto.book.BookDto;
import twitterclon.domain.dto.book.request.BookRequest;
import twitterclon.domain.entity.BookEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {
    private final BookService bookService;
    private final IBookDtoMapper iBookDtoMapper;
    private final IBookRequestMapper iBookRequestMapper;

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> bookEntityList = bookService.findAll();
        return iBookDtoMapper.toBookDtoList(bookEntityList);
    }

    @Override
    public BookDto findById(Long id) {
        BookEntity bookEntity = bookService.findById(id);
        return iBookDtoMapper.toBookDto(bookEntity);
    }

    @Override
    public BookDto create(BookRequest bookRequest) {
        BookEntity bookEntityToCreate = iBookRequestMapper.toBookEntity(bookRequest);
        BookEntity bookEntityAdded = bookService.create(bookEntityToCreate);
        return iBookDtoMapper.toBookDto(bookEntityAdded);
    }

    @Override
    public BookDto update(Long id,
                          BookRequest bookRequest) {
        BookEntity bookEntityToUpdate = iBookRequestMapper.toBookEntity(bookRequest);
        bookEntityToUpdate.setId(id);
        BookEntity bookEntityUpdated = bookService.update(bookEntityToUpdate);
        return iBookDtoMapper.toBookDto(bookEntityUpdated);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }
}
