package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.User;

import java.util.List;

public interface ICompanyService {
    public List<Company> findAll();

    public Company findCompanyById(Long id);

    public Company addCompany(Company c);
    public void DeleteCompany(Long id);

    public Company updateCompany(Long id , Company e);

    void assignResponsible(Long companyId, Long userId);

    Company findCompanyByResponsable (User user);
}
