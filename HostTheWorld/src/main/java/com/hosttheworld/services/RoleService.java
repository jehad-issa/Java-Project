package com.hosttheworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosttheworld.models.Role;
import com.hosttheworld.repositories.RoleRepository;

 
@Service
public class RoleService {
	
	@Autowired
    private RoleRepository roleRepository;
    
    public List<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    
}

