package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    public JobOffer findJobOfferById(Long idjoboffer);
    public List<JobOffer> findJobOffersByEntrepriseOrderByDatePublicationDesc(Company company);
}
