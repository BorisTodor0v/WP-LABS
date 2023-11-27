package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService{
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    Book findBookById(Long id);
    void deleteById(Long id);

    void update(Long bookId, String title, String isbn, String genre, int year, BookStore bookStore);

    void addNewBook(Book book);
}
