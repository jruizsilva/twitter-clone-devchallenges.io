package twitterclon.business.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import twitterclon.business.facade.IBookFacade;
import twitterclon.business.mapper.book.IBookDtoMapper;
import twitterclon.business.mapper.book.IBookRequestMapper;
import twitterclon.business.service.IBookService;
import twitterclon.domain.dto.BookDto;
import twitterclon.domain.dto.request.BookRequest;
import twitterclon.domain.entity.BookEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IBookFacadeImpl implements IBookFacade {
    private final IBookService iBookService;
    private final IBookDtoMapper iBookDtoMapper;
    private final IBookRequestMapper iBookRequestMapper;

    @Override
    public List<BookDto> findAll() {
        List<BookEntity> bookEntityList = iBookService.findAll();
        return iBookDtoMapper.toBookDtoList(bookEntityList);
    }

    @Override
    public BookDto findById(Long id) {
        BookEntity bookEntity = iBookService.findById(id);
        return iBookDtoMapper.toBookDto(bookEntity);
    }

    @Override
    public BookDto create(BookRequest bookRequest) {
        BookEntity bookEntityToCreate = iBookRequestMapper.toBookEntity(bookRequest);
        BookEntity bookEntityAdded = iBookService.create(bookEntityToCreate);
        return iBookDtoMapper.toBookDto(bookEntityAdded);
    }

    @Override
    public BookDto update(Long id,
                          BookRequest bookRequest) {
        BookEntity bookEntityToUpdate = iBookRequestMapper.toBookEntity(bookRequest);
        bookEntityToUpdate.setId(id);
        BookEntity bookEntityUpdated = iBookService.update(bookEntityToUpdate);
        return iBookDtoMapper.toBookDto(bookEntityUpdated);
    }

    @Override
    public void deleteById(Long id) {
        iBookService.deleteById(id);
    }
}
