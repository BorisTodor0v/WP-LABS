package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    List<Review> findReviewsByBook(Book book);
    void addNewReview(Review review);
    List<Review> getReviewsByBookAndTimeStampBetween(Book book, LocalDateTime from, LocalDateTime to);
}
