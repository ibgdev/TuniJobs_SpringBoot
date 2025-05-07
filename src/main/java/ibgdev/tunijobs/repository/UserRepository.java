package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.Roles;
import ibgdev.tunijobs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(Long idUser);
    public User findUserByEmail(String email);
    public List<User> findUserByRole(Roles role);
}
