package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService{
    List<Book> listBooks();
    Book findBookByIsbn(String isbn);
    Book findBookById(Long id);
    List<Book> findBooksByStore(BookStore bookStore);
    void deleteById(Long id);
    void update(Book book);
    void addNewBook(Book book);
    void addAuthorToBook(Book book, Author author);
}
