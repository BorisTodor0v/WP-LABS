package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;

    public BookController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService, ReviewService reviewService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
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
        List<Review> reviews = reviewService.findReviewsByBook(book);
        model.addAttribute("b", book);
        model.addAttribute("Authors", authors);
        model.addAttribute("reviews", reviews);
        return "bookDetails";
    }

    @GetMapping("/details/{bookId}/review")
    public String getAddReviewPage(@PathVariable Long bookId, Model model){
        Book book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        return "addReview";
    }

    @PostMapping("/details/{bookId}/review")
    public String addReviewForBook(@PathVariable Long bookId,
                                   @RequestParam Integer score,
                                   @RequestParam String description,
                                   @RequestParam(required = false) LocalDateTime timeStamp){
        if(timeStamp == null){ // Create new review with timeStamp at time of submitting
            timeStamp = LocalDateTime.now();
        } // Otherwise, use the timestamp passed from the form
        if(description.isEmpty()){
            description = "(no description available)";
        }
        Book book = bookService.findBookById(bookId);
        Review review = new Review(score, description, book, timeStamp);
        reviewService.addNewReview(review);
        return "redirect:/books/details/{bookId}";
    }

    @PostMapping("/details/{bookId}/review/filtered")
    public String getFilteredReviewsPage(@PathVariable Long bookId,
                                         @RequestParam LocalDateTime from,
                                         @RequestParam LocalDateTime to,
                                         Model model){
        Book book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        List<Review> reviews = reviewService.getReviewsByBookAndTimeStampBetween(book, from, to);
        model.addAttribute("reviews", reviews);
        return "bookReviewsFiltered";
    }

    //@PostMapping("/details/{bookId}/reviews/filtered")


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
        bookService.addNewBook(book);
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
        Book book = bookService.findBookById(bookId);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setGenre(genre);
        book.setYear(year);
        BookStore bookStore = bookStoreService.findBookStoreById(bookStoreId);
        book.setBookStore(bookStore);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}