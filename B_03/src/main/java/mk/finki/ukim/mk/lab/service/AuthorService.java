package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService{
    List<Author> listAuthors();
    Optional<Author> findById(Long id);
    List<Author> findAllByIds(List<Long> authorIds);
    void addNewAuthor(Author author);
}