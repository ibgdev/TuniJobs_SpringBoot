package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    public Application findApplicationsById(Long idApplication);
}
