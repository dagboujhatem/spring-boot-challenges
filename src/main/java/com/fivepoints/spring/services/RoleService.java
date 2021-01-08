package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.Role;
import com.fivepoints.spring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public String addRole(Role role) {
        roleRepository.save(role);
        return "Role added successfully";
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByID(int id) {
        return roleRepository.findById(id).get();
    }

    public Role updateRoleByID(int id, Role role) {
        Role existingRole = roleRepository.findById(id).orElse(null);
        if(existingRole != null)
        {
            existingRole.setName(role.getName());
        }
        return roleRepository.save(existingRole);
    }

    public String deleteRoleByID(int id) {
        Optional<Role> existingRole = roleRepository.findById(id);
        if(existingRole.isPresent()) {
            roleRepository.delete(existingRole.get());
            return "Role is deleted by id "+ id;
        }
        throw new RuntimeException("Role not found .");
    }
}
