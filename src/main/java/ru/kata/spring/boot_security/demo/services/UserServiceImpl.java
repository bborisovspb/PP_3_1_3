package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }
    public User userInfo(int id){
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }
    @Transient
    public void saveUser(User user){
        usersRepository.save(user);
    }
    public void updateUser(User user){
        usersRepository.save(user);
    }
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> users = usersRepository.findByUsername(s);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return users.get();
        }
    }
}
