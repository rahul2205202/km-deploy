package com.krushimarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krushimarket.dto.LoginDto;
import com.krushimarket.dto.ProductDto;
import com.krushimarket.dto.UserCreateDto;
import com.krushimarket.dto.UserDto;
import com.krushimarket.dto.UserUpdateDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.jwt.JwtUtils;
import com.krushimarket.model.User;
import com.krushimarket.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public UserDto registerUser(UserCreateDto createDto) {
        User user = mapper.map(createDto, User.class);
        String encodedPassword = encoder.encode(createDto.getPassword());
        user.setPassword(encodedPassword);
        User registeredUser = repository.save(user);
        if (registeredUser == null) {
            throw new CustomException("User Registration Failed");
        }
        return mapper.map(registeredUser, UserDto.class);
    }

	public List<UserDto> getAllUsers() {
		List<User> users = repository.findAll();
		if(users.isEmpty()) {
			throw new CustomException("No Users Found");
		}
		return users.stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
	}

	public UserUpdateDto updateUser(Long id, UserUpdateDto dto) {
	    User existingUser = repository.findById(id)
	            .orElseThrow(() -> new CustomException("User not found"));

	    BeanUtils.copyProperties(dto, existingUser, "userID", "phone", "password", "createdAt", "products", "subscription", "userReviews", "notifications"); 
//	    existingUser = mapper.map(dto, User.class);
	    User updatedUser = repository.save(existingUser);
	    return mapper.map(updatedUser, UserUpdateDto.class);
	}
	
	public UserDto loginUser(LoginDto loginDto) {
		System.out.println(loginDto);
		User user = repository.findByEmail(loginDto.getEmail()).orElseThrow();
		System.out.println(user);
		if(user.getPassword().equals(loginDto.getPassword())) {
			return mapper.map(user, UserDto.class);
		}
		throw new CustomException("Login Failed");
	}

	public UserDto getUserProfile(String jwt) {
        String email = jwtUtils.getUsernameFromToken(jwt); // Extract username (email) from JWT
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found"));
        return mapper.map(user, UserDto.class);
    }

	public String changeImage(Long userId, String imageUrl) {
		User user = repository.findById(userId).orElseThrow();
		if(user!=null) {
			user.setImagePath(imageUrl);
			repository.save(user);
		}else {
			throw new CustomException("Image not Changed");
		}
		return "Image Changed";
	}
}
