package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Company;
import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.CompanyRepository;
import ibgdev.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User addUser(User c) {
        c.setPassword(passwordEncoder.encode(c.getPassword()));
        return userRepository.save(c);
    }

    @Override
    public void DeleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User e) {
        User u = userRepository.findUserById(id);
        if (u != null) {
            u.setNom(e.getNom());
            u.setEmail(e.getEmail());

            if (e.getPassword() != null && !e.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(e.getPassword());
                u.setPassword(encodedPassword);
            }

            // Si changement de rôle ENTREPRISE vers autre chose, dissocier la company
            if (u.getRole() == Roles.ENTERPRISE && e.getRole() != Roles.ENTERPRISE) {
                Company company = companyRepository.findCompanyByResponsable(u);
                if (company != null) {
                    company.setResponsable(null);
                    companyRepository.save(company);
                }
            }

            u.setRole(e.getRole());
            u.setCreatedAt(e.getCreatedAt());
            u.setReviews(e.getReviews());
            u.setApplications(e.getApplications());

            return userRepository.save(u);
        }
        return null;
    }


    @Override
    public List<User> FindUserByRole(Roles role) {
        return userRepository.findUserByRole(role);
    }

}
