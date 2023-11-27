package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<Book> books = new ArrayList<>();

    public BookStore(String name, String city, String address){
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
