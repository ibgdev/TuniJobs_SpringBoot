package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    public Company findCompanyById(Long idCompany);
}
