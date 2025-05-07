package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.CompanyRepository;
import ibgdev.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class CompanyService implements ICompanyService{
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findCompanyById(id);
    }

    @Override
    public Company addCompany(Company c) {
        return companyRepository.save(c);
    }

    @Override
    public void DeleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company updateCompany(Long id, Company e) {
        Company c = companyRepository.findCompanyById(id);
        if (c != null){
            c.setId(e.getId());
            c.setNom(e.getNom());
            c.setMatriculeFiscale(e.getMatriculeFiscale());
            c.setSecteur(e.getSecteur());
            c.setAdresse(e.getAdresse());
            c.setTelephone(e.getTelephone());
            c.setSiteWeb(e.getSiteWeb());
            c.setResponsable(e.getResponsable());
            c.setJoboffers(e.getJoboffers());
            c.setReviews(e.getReviews());
            return companyRepository.save(c);
        }
        return null;
    }

    @Override
    public void assignResponsible(Long companyId, Long userId) {
        Company company = companyRepository.findCompanyById(companyId);
        User user = userRepository.findById(userId).orElse(null);

        if (company != null && user != null) {
            company.setResponsable(user);
            user.setRole(Roles.ENTERPRISE);
            companyRepository.save(company);
            userRepository.save(user);
        }
    }
}
