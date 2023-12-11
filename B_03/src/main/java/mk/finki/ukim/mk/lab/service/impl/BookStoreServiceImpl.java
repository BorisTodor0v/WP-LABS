package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findBookStoreByName(String name) {
        return bookStoreRepository.findBookStoreByName(name);
    }

    @Override
    public BookStore findBookStoreById(Long bookStoreId) {
        return bookStoreRepository.findBookStoreById(bookStoreId);
    }

    @Override
    public void addNewBookStore(BookStore bookStore) {
        bookStoreRepository.save(bookStore);
    }
}
