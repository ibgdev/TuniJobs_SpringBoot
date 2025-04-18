package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();

    public User findUserById(Long id);

    public User addUser(User c);
    public void DeleteUser(Long id);

    public User updateUser(Long id , User e);
}
