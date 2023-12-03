package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Order(3)
public class BookRepository {

    public static List<Book> books = new ArrayList<>();

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @PostConstruct
    public void init(){
        List<Author> authors = authorRepository.findAll();
        List<BookStore> bookStores = bookStoreRepository.findAll();

        List<Author> book1Authors = new ArrayList<>();
        book1Authors.add(authors.get(0));
        books.add(new Book("isbn1", "Title1", "Genre1", 2000, book1Authors, bookStores.get(0)));
        List<Author> book2Authors = new ArrayList<>();
        book2Authors.add(authors.get(1));
        books.add(new Book("isbn2", "Title2", "Genre2", 2001, book2Authors, bookStores.get(1)));
        List<Author> book3Authors = new ArrayList<>();
        book3Authors.add(authors.get(2));
        books.add(new Book("isbn3", "Title3", "Genre3", 2002, book3Authors, bookStores.get(2)));
        List<Author> book4Authors = new ArrayList<>();
        book4Authors.add(authors.get(3));
        books.add(new Book("isbn4", "Title4", "Genre4", 2003, book4Authors, bookStores.get(3)));
        List<Author> book5Authors = new ArrayList<>();
        book5Authors.add(authors.get(4));
        books.add(new Book("isbn5", "Title5", "Genre5", 2004, book5Authors, bookStores.get(4)));
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findByIsbn(String isbn) {
        return books.stream().filter(b->b.getIsbn().equals(isbn)).collect(Collectors.toList()).get(0);
    }

    public Book findById(Long id){
        return books.stream().filter(b->b.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    public List<Book> findByBookStore(BookStore bookStore){
        return books.stream().filter(b->b.getBookStore().equals(bookStore)).collect(Collectors.toList());
    }

    public Author addAuthorToBook(Author author, Book book) {
        book.getAuthors().add(author);
        return author;
    }



    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public void update(Long bookId, String title, String isbn, String genre, int year, BookStore bookStore) {
        findById(bookId).setTitle(title);
        findById(bookId).setIsbn(isbn);
        findById(bookId).setGenre(genre);
        findById(bookId).setYear(year);
        findById(bookId).setBookStore(bookStore);
    }

    public void addNewBook(Book book) {
        books.add(book);
    }
}
