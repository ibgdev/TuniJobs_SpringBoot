package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Review;

import java.util.List;

public interface IReviewService {
    public List<Review> findAll();

    public Review findReviewById(Long id);

    public Review addReview(Review c);
    public void DeleteReview(Long id);

    public Review updateReview(Long id , Review e);
}
