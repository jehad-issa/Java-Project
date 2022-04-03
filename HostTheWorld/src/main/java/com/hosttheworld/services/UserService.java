package com.hosttheworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosttheworld.models.Role;
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

	 public User findUserById(long id) {
	     Optional<User> optionalDojo = userRepository.findById(id);
	     if(optionalDojo.isPresent()) {
	         return optionalDojo.get();
	     } else {
	         return null;
	     }
	 }


	public List<User> findAll() {
		
		return userRepository.findAll();
	}


	 //Delete the user
	 public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}


	public List<User> findAllHosts() {
		List<User> users = findAll();
		ArrayList<User> hosts = new ArrayList<>();
		for (User user: users) {
			if(user.getRoles().get(0).getName().equals("ROLE_HOST")) {
				hosts.add(user);
			}
		}
		return hosts;
	}

	public List<User> findByCountryContain(String search) {
		return userRepository.findByCountryIsContaining(search);
		
	}


	public void makeUserAdmin(User user, List<Role> role) {
		user.setRoles(role);
		userRepository.save(user);
	}
	
	
}

