package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author/{bookId}")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    String getAuthorsPage(@PathVariable Long bookId, @RequestParam(required = false) String error, Model model){
        Book book = bookService.findBookById(bookId);
        List<Author> authors = authorService.listAuthors();
        model.addAttribute("Authors", authors);
        model.addAttribute("book", book);
        model.addAttribute("error", error);
        return "authorList";
    }

    @PostMapping
    String addAuthors(@PathVariable Long bookId,
                      @RequestParam Long authorId){
        bookService.findBookById(bookId).getAuthors().add(authorService.findById(authorId).get());
        return "redirect:/books/details/{bookId}";
    }


}
