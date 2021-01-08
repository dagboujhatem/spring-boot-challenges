package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> addUser(@RequestBody User user) {
        String message = userService.addUser(user);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value="id") int id) {
        User user = userService.getUserByID(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public User updateUserByID(@PathVariable(value="id") int id, @RequestBody User user) {
        return userService.updateUserByID(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable(value="id") int user) {
        String message = userService.deleteUserByID(user);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

}
