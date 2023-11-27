package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorService) {
        this.authorRepository = authorService;
    }


    @Override
    public List<Author> listAuthors()
    {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAllByIds(List<Long> authorIds) {
        return authorRepository.findAllByIds(authorIds);
    }
}
