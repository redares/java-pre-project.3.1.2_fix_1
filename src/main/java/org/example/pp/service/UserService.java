package org.example.pp.service;

import org.example.pp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUserById(Long id);
}
