package mk.ukim.finki.wp.lab.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.bookService;
import mk.ukim.finki.wp.lab.repository.bookRepository;
import mk.ukim.finki.wp.lab.repository.authorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class bookServiceImpl implements bookService {
    private final bookRepository bookRepository;
    private final authorRepository authorRepository;

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author newAuthor = authorRepository.findByID(authorId).get();
        bookRepository.addAuthorToBook(newAuthor, bookRepository.findByIsbn(isbn).get());
        return newAuthor;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).get();
    }
}
