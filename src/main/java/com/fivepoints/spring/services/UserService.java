package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(int id) {
        return userRepository.findById(id).get();
    }

    public User updateUserByID(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null)
        {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setAge(user.getAge());
        }
        return userRepository.save(existingUser);
    }

    public String deleteUserByID(int id) {
        Optional<User> empl = userRepository.findById(id);
        if(empl.isPresent()) {
            userRepository.delete(empl.get());
            return "User is deleted by id "+ id;
        }
        throw new RuntimeException("User not found .");
    }
}
