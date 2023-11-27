package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    String getBooksPage(@RequestParam(required = false) String error, Model model){
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);
        model.addAttribute("error", error);
        return "listBooks";
    }

    @GetMapping("/details/{bookId}")
    public String getBookDetailsPage(@PathVariable Long bookId, Model model){
        Book book = bookService.findBookById(bookId);
        List<Author> authors = book.getAuthors();
        model.addAttribute("b", book);
        model.addAttribute("Authors", authors);
        return "bookDetails";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {
        List<Author> authors = authorService.listAuthors();
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bookStores", bookStores);
        return "addBook";
    }

    @PostMapping("/add-form")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam List<Long> authorIds,
                           @RequestParam Long bookStoreId) {
        List<Author> authors = authorService.findAllByIds(authorIds);
        BookStore bookStore = bookStoreService.findBookStoreById(bookStoreId);
        Book book = new Book(isbn, title, genre, year, authors, bookStore);
        bookService.addNewBook (book);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{bookId}")
    public String getEditBookForm(@PathVariable Long bookId, Model model) {
        Book book = bookService.findBookById(bookId);
        List<BookStore> bookStores = bookStoreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("bookStores", bookStores);
        return "editBook";
    }

    @PostMapping("/edit-form/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long bookStoreId) {

        BookStore bookStore = bookStoreService.findBookStoreById(bookStoreId);

        bookService.update(bookId, title, isbn, genre, year, bookStore);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}