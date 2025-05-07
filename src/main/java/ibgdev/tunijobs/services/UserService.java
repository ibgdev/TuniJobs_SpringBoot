package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

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
            u.setId(id);
            u.setNom(e.getNom());
            u.setEmail(e.getEmail());
            u.setPassword(e.getPassword());
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
