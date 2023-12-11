package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.ReviewRepository;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findReviewsByBook(Book book) {
        return reviewRepository.findReviewsByBook(book);
    }

    @Override
    public void addNewReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByBookAndTimeStampBetween(Book book, LocalDateTime from, LocalDateTime to) {
        return reviewRepository.getReviewsByBookAndTimeStampBetween(book, from, to);
    }
}
