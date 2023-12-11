package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    String getAuthorsPage(@PathVariable Long bookId, @RequestParam(required = false) String error, Model model){
        Book book = bookService.findBookById(bookId);
        List<Author> authors = authorService.listAuthors();
        model.addAttribute("Authors", authors);
        model.addAttribute("book", book);
        model.addAttribute("error", error);
        return "authorList";
    }

    @PostMapping("/{bookId}")
    String addAuthors(@PathVariable Long bookId,
                      @RequestParam Long authorId){
        bookService.addAuthorToBook(bookService.findBookById(bookId), authorService.findById(authorId).get());
        return "redirect:/books/details/{bookId}";
    }

    @GetMapping("/add")
    String getAddAuthorPage(){
        return "addAuthor";
    }

    @PostMapping("/add")
    String saveAuthor(@RequestParam String name,
                      @RequestParam String surname,
                      @RequestParam String biography,
                      @RequestParam LocalDate dateOfBirth){
        Author author = new Author(name, surname, biography, dateOfBirth);
        authorService.addNewAuthor(author);
        return "redirect:/books";
    }


}
