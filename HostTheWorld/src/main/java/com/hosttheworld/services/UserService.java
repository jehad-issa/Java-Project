package com.hosttheworld.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosttheworld.models.User;
import com.hosttheworld.repositories.RoleRepository;
import com.hosttheworld.repositories.UserRepository;

 
@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    
    // 1
    public void saveWithHostRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_HOST"));
        userRepository.save(user);
    }
    
    // 2
    public void saveWithVisitorRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_VISITOR"));
        userRepository.save(user);
    }
     
     
     // 3
    public void saveWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }
    
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


	public List<User> findAll() {
		
		return userRepository.findAll();
	}


	 //Delete the user
	 public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}

