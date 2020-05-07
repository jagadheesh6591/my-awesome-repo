package com.jaga.usermanagement.service;

import com.jaga.usermanagement.entity.User;
import com.jaga.usermanagement.repository.UserRepository;
import com.jaga.usermanagement.view.UserLogin;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User findUser(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException(String.valueOf(id));
        }
        return this.userRepository.findById(id).get();
    }

    public User createUser(User user) {
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByEmail(user.getEmail()));
        if (userOptional.isPresent()) {
            throw new EntityExistsException(user.getEmail());
        }
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        User userPersist = findUserByEmail(user);
        userPersist.setName(user.getName());
        userPersist.setEmail(user.getEmail());
        userPersist.setPassword(user.getPassword());
        return this.userRepository.save(userPersist);
    }

    public void removeUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findUserByEmail(User user) {
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByEmail(user.getEmail()));
        return validateUserExistance(userOptional,user.getEmail());
    }

    @SneakyThrows
    public void validateLogin(UserLogin userLogin) {
        String username = userLogin.getUsername(); // mailId as username
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByEmail(username));
        User user = validateUserExistance(userOptional, username);
        if(!user.getPassword().equals(userLogin.getPassword())) {
            throw new LoginException(username);
        }

        // update time of login
        user.setLastLogin(new Date());
        updateUser(user);
    }

    public User validateUserExistance(Optional<User> userOptional, String mailId) {
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException(mailId);
        }
        return userOptional.get();
    }
}
