package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.authorService;
import mk.ukim.finki.wp.lab.service.bookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/bookDetails")
@AllArgsConstructor
public class BookDetailsServlet extends HttpServlet {
    private final bookService bookService;
    private final authorService authorService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);
        String bookISBN = (String) req.getSession().getAttribute("bookISBN");
        Long authorId = Long.valueOf(req.getParameter("authorId"));
        bookService.addAuthorToBook(authorId,bookISBN);
        Book book = bookService.findBookByIsbn(bookISBN);
        context.setVariable("book", book);
        springTemplateEngine.process(
                "bookDetails.html",
                context,
                resp.getWriter()
        );
    }
}
