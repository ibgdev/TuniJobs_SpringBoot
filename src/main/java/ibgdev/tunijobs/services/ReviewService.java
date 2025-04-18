package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Review;
import ibgdev.tunijobs.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findReviewById(Long id) {
        return reviewRepository.findReviewById(id);
    }

    @Override
    public Review addReview(Review c) {
        return reviewRepository.save(c);
    }

    @Override
    public void DeleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review updateReview(Long id, Review e) {
        Review r = reviewRepository.findReviewById(id);
        if(r != null) {
            r.setId(id);
            r.setNote(e.getNote());
            r.setCommentaire(e.getCommentaire());
            r.setDate(e.getDate());
            r.setEntreprise(e.getEntreprise());
            r.setUser(e.getUser());
            return reviewRepository.save(r);
        }
        return null;
    }
}
