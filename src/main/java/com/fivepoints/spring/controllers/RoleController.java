package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.Role;
import com.fivepoints.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/")
    public String addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getUserByID(@PathVariable(value="id") int id) {
        Role role = roleService.getRoleByID(id);
        if(role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(role);
    }

    @PutMapping("/{id}")
    public Role updateUserByID(@PathVariable(value="id") int id, @RequestBody Role role) {
        return roleService.updateRoleByID(id, role);
    }

    @DeleteMapping("/{id}")
    public String deleteRoleByID(@PathVariable(value="id") int user) {
        return roleService.deleteRoleByID(user)	;
    }

}
