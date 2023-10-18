package twitterclon.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twitterclon.domain.entity.BookEntity;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {

}
