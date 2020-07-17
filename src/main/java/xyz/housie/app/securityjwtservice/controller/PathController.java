package xyz.housie.app.securityjwtservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.housie.app.securityjpaservice.role.model.Role;
import xyz.housie.app.securityjpaservice.role.repository.RoleRepository;
import xyz.housie.app.securityjpaservice.user.model.User;
import xyz.housie.app.securityjpaservice.user.repository.UserRepository;

@RestController
public class PathController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String home(){
        return "Welcome Home";
    }

    @RequestMapping("/user")
    public String user(){
        return "Welcome User";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Welcome Admin";
    }

    @PostMapping("/register/{role_name}")
    public void register(@RequestBody User user, @PathVariable String role_name){
        logger.info(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByRole(role_name);

        if(role == null) {
            role = roleRepository.insert(new Role(role_name));
        }

        user.getRoles().add(role);
        userRepository.insert(user);
    }
}
