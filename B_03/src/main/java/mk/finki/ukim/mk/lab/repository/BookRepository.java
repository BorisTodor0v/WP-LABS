package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    Book findByIsbn(String isbn);
    Optional<Book> findById(Long id);
    List<Book> findBooksByBookStore(BookStore bookStore);
    void deleteBookById(Long id);
    Book save(Book book);
}