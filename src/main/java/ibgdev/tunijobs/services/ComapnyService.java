package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComapnyService implements ICompanyService{
    @Autowired
    CompanyRepository companyRepository;

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
            c.setMatriquleFiscale(e.getMatriquleFiscale());
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
}
