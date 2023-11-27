package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book findBookByIsbn = bookRepository.findByIsbn(isbn);
        Optional<Author> findAuthorById = authorRepository.findById(authorId);

        if(findAuthorById.isEmpty()){
            throw new IllegalArgumentException();
        }

        return bookRepository.addAuthorToBook(findAuthorById.get(),findBookByIsbn);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Long bookId, String title, String isbn, String genre, int year, BookStore bookStore) {
        bookRepository.update(bookId, title, isbn, genre, year, bookStore);
    }

    @Override
    public void addNewBook(Book book) {
        bookRepository.addNewBook(book);
    }

}
