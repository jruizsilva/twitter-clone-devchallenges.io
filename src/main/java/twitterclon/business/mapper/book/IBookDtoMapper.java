package twitterclon.business.mapper.book;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import twitterclon.domain.dto.book.BookDto;
import twitterclon.domain.entity.BookEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBookDtoMapper {
    BookDto toBookDto(BookEntity bookEntity);
    List<BookDto> toBookDtoList(List<BookEntity> bookEntityList);
}
