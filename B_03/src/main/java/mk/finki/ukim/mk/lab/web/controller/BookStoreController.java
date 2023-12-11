package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
Nov controller za bookstore so ke gi lista site bookstores
za sekoj bookstore da ima details koe ke se klikne ke gi izlista site knigi
 od bookstore
 */

@Controller
@RequestMapping("/bookStores")
public class BookStoreController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookStoreController(BookService bookService, BookStoreService bookStoreService){
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    String getBookStoresPage(@RequestParam(required = false) String error, Model model){
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("bookStores", bookStores);
        model.addAttribute("error", error);
        return "listBookStores";
    }

    @GetMapping("/details/{bookStoreId}")
    public String getBookStoreDetailsPage(@PathVariable Long bookStoreId, Model model){
        BookStore bookStore = bookStoreService.findBookStoreById(bookStoreId);
        List<Book> books = bookService.findBooksByStore(bookStore);
        model.addAttribute("bookStoreName", bookStore.getName());
        model.addAttribute("books", books);
        return "bookStoreDetails";
    }

    @GetMapping("/add")
    public String getNewBookStoreForm(){
        return "addBookStore";
    }

    @PostMapping("/add")
    public String addNewBookStore(@RequestParam String name,
                                  @RequestParam String city,
                                  @RequestParam String address){
        BookStore bookStore = new BookStore(name, city, address);
        bookStoreService.addNewBookStore(bookStore);
        return "redirect:/books";
    }

}