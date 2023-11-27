package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Order(1)
public class AuthorRepository {
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init(){
        authors.add(new Author(1L, "Aka", "Akasaka", "Manga"));
        authors.add(new Author(2L, "Rei", "Miyajima", "Peak"));
        authors.add(new Author(3L, "George", "Orwell", "Literally"));
        authors.add(new Author(4L, "Takehiko", "Inoue", "Philosophy"));
        authors.add(new Author(5L, "Neznam","Avtori","nepismensum"));
    }
    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(Long id) {
        return authors.stream().filter(a->a.getId().equals(id)).findFirst();
    }

    public List<Author> findAllByIds(List<Long> authorIds) {
        return authors.stream()
                .filter(author -> authorIds.contains(author.getId()))
                .collect(Collectors.toList());
    }
}
