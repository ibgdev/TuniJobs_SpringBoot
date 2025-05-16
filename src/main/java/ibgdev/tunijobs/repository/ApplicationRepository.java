package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Application;
import ibgdev.tunijobs.entity.JobOffer;
import ibgdev.tunijobs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    public Application findApplicationsById(Long idApplication);
    Optional<Application> findByUserAndJobOffer(User user, JobOffer jobOffer);
    List<Application> findApplicationsByUser(User user);
    List<Application> findApplicationsByJobOffer(JobOffer jobOffer);

    @Query("SELECT a FROM Application a WHERE a.jobOffer.entreprise.id = :companyId")
    List<Application> findAllByCompanyId(Long companyId);
}
