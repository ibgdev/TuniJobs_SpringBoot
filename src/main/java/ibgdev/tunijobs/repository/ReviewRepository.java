package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public Review findReviewById(Long idReview);
}
