package com.sasayaki7.admindashboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sasayaki7.admindashboard.models.Role;
import com.sasayaki7.admindashboard.models.User;
import com.sasayaki7.admindashboard.repositories.RoleRepository;
import com.sasayaki7.admindashboard.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void saveUserWithRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        System.out.println("User");
        userRepository.save(user);
	}
	
	public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        System.out.println("Admin");

        userRepository.save(user);
	}
	
	public void saveUserWithSuperAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_SYSADMIN"));
        System.out.println("Sysadmin");

        userRepository.save(user);
	}
	
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
    public User findUser(Long id) {
    	Optional<User> tempUser = userRepository.findById(id);
    	if(tempUser.isPresent()) {
    		return tempUser.get();
    	}
    	else {
    		return null;
    	}
    }
    
    public List<User> allUsers() {
    	return userRepository.findAll();
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
    
    
    public User updateUser(User u) {
    	return userRepository.save(u);
    }
    
    public List<Role> findRole(String name) {
    	return roleRepository.findByName(name);
    }
    
    public int getAdminCount() {
    	return roleRepository.getAdmin();
    }
    
}
