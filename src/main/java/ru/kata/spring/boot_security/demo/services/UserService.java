package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.Transient;
import java.util.Optional;

public interface UserService {

    public User userInfo(int id);
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);


}
