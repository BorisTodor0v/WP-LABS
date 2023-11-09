package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.authorRepository;
import mk.ukim.finki.wp.lab.service.authorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authorServiceImpl implements authorService {
    private final authorRepository authorRepository;

    public authorServiceImpl(mk.ukim.finki.wp.lab.repository.authorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findByID(id).get();
    }
}
