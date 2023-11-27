package mk.finki.ukim.mk.lab.repository;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Order(2)
public class BookStoreRepository {
    public static List<BookStore> bookStores = new ArrayList<>();

    @PostConstruct
    public void init(){
        bookStores.add(new BookStore("Bookstore 1", "City 1", "Address 1"));
        bookStores.add(new BookStore("Bookstore 2", "City 2", "Address 2"));
        bookStores.add(new BookStore("Bookstore 3", "City 3", "Address 3"));
        bookStores.add(new BookStore("Bookstore 4", "City 4", "Address 4"));
        bookStores.add(new BookStore("Bookstore 5", "City 5", "Address 5"));
    }

    public List<BookStore> findAll(){
        return bookStores;
    }

    public BookStore findBookStoreByName(String name) {
        return bookStores.stream().filter(b->b.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public BookStore findBookStoreById(Long bookStoreId) {
        return bookStores.stream().filter(b->b.getId().equals(bookStoreId)).collect(Collectors.toList()).get(0);
    }
}
