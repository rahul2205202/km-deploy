package com.krushimarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User; // Import Spring Security's User
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.krushimarket.repository.UserRepository;

import java.util.Collections; // For empty authorities if needed
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
        	Optional<com.krushimarket.model.User> userOptional = userRepository.findByEmail(email);
            com.krushimarket.model.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return null;
        
    }
}