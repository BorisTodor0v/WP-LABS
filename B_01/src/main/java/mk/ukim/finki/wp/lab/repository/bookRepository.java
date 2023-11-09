package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class bookRepository {
    private List<Book> books = null;

    @PostConstruct
    public void init(){
        books.add(new Book("isbn1","Title1","Genre1",2000, (List<Author>) authorRepository.authors.get(0)));
        books.add(new Book("isbn2","Title2","Genre2",2001, (List<Author>) authorRepository.authors.get(1)));
        books.add(new Book("isbn3","Title3","Genre3",2002, (List<Author>) authorRepository.authors.get(2)));
        books.add(new Book("isbn4","Title4","Genre4",2003, (List<Author>) authorRepository.authors.get(3)));
        books.add(new Book("isbn5","Title5","Genre5",2004, (List<Author>) authorRepository.authors.get(4)));
    }

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findByIsbn(String isbn){
        return books.stream().filter(a->a.getIsbn().equals(isbn)).findFirst();
    }

    public Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);
        return author;
    }

}
