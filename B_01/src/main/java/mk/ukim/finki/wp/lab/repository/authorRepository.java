package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class authorRepository {
    public static List<Author> authors = null;

    @PostConstruct
    public void init(){
        authors.add(new Author(1L, "Aka", "Akasaka", "Manga"));
        authors.add(new Author(2L, "Rei", "Miyajima", "Peak"));
        authors.add(new Author(3L, "George", "Orwell", "Literally"));
        authors.add(new Author(4L, "Takehiko", "Inoue", "Philosophy"));
        authors.add(new Author(5L, "Neznam","Avtori","nepismensum"));
    }

    public List<Author> findAll(){
        return authors;
    }
    public Optional<Author> findByID(Long id){
        return authors.stream().filter(a->a.getId().equals(id)).findFirst();
    }
}
