package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    public JobOffer findJobOfferById(Long idjoboffer);
}
