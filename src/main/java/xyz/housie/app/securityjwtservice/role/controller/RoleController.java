package xyz.housie.app.securityjwtservice.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.housie.app.securityjwtservice.role.model.Role;
import xyz.housie.app.securityjwtservice.role.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/add/{role_name}")
    public Role addRole(@PathVariable String role_name){
        Role role = roleRepository.findRoleByRole(role_name);
        return role == null ? roleRepository.insert(new Role(role_name)) : role;
    }
}
