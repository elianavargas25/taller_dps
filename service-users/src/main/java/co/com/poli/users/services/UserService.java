package co.com.poli.users.services;

import co.com.poli.users.entities.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void  delete(User user);
    User findById(Long id);
    List<User> findAll();

}
