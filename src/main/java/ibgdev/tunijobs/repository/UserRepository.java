package ibgdev.tunijobs.repository;

import ibgdev.tunijobs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(Long idUser);
}
