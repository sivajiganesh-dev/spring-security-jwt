package xyz.housie.app.securityjwtservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.housie.app.securityjpaservice.user.model.User;
import xyz.housie.app.securityjpaservice.user.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userRepository.insert(user);
    }
}
