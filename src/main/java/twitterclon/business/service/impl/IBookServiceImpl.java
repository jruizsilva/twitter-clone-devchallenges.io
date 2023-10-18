package twitterclon.business.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import twitterclon.business.service.IBookService;
import twitterclon.common.exception.BookException;
import twitterclon.common.util.constants.BookConstants;
import twitterclon.domain.entity.BookEntity;
import twitterclon.persistence.IBookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class IBookServiceImpl implements IBookService {
    private final IBookRepository iBookRepository;

    @Override
    public List<BookEntity> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public BookEntity findById(Long id) {
        Optional<BookEntity> bookEntityOptional = iBookRepository.findById(id);
        if (bookEntityOptional.isEmpty()) {
            throw new BookException(HttpStatus.NOT_FOUND,
                                    String.format(BookConstants.BOOK_NOT_FOUND_MESSAGE_ERROR, id));
        }
        return bookEntityOptional.get();
    }

    @Override
    public BookEntity create(BookEntity bookEntity) {
        return iBookRepository.save(bookEntity);
    }

    @Override
    public BookEntity update(BookEntity bookEntity) {
        Optional<BookEntity> bookEntityOptional = iBookRepository.findById(bookEntity.getId());
        if (bookEntityOptional.isEmpty()) {
            throw new BookException(HttpStatus.NOT_FOUND,
                                    String.format(BookConstants.BOOK_NOT_FOUND_MESSAGE_ERROR, bookEntity.getId()));
        }
        return iBookRepository.save(bookEntity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<BookEntity> bookEntityOptional = iBookRepository.findById(id);
        if (bookEntityOptional.isEmpty()) {
            throw new BookException(HttpStatus.NOT_FOUND,
                                    String.format(BookConstants.BOOK_NOT_FOUND_MESSAGE_ERROR, id));
        }
        iBookRepository.deleteById(id);
    }

}
