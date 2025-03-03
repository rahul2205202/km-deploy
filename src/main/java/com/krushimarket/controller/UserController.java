package com.krushimarket.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.krushimarket.dto.AuthResponse;
import com.krushimarket.dto.LoginDto;
import com.krushimarket.dto.UserCreateDto;
import com.krushimarket.dto.UserDto;
import com.krushimarket.dto.UserUpdateDto;
import com.krushimarket.exception.CustomException;
import com.krushimarket.jwt.JwtUtils;
import com.krushimarket.model.User;
import com.krushimarket.service.AWSService;
import com.krushimarket.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@Autowired
	private AWSService awsService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@RequestHeader("Authorization") String token) {
		System.out.println(token);
        try {
            String jwt = token.substring(7);
            UserDto user = service.getUserProfile(jwt);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
	
	@GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
		try {
			List<UserDto> users = service.getAllUsers();
	        return ResponseEntity.ok(users);
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
	
	  @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestParam("image") MultipartFile image,
	                                          @ModelAttribute UserCreateDto createDto) {
		  System.out.println(image);
		  System.out.println(createDto);

	        try {
	            String imageUrl = null;

	            if (image != null && !image.isEmpty()) {
	                imageUrl = awsService.uploadImage(image);
	            }

	            createDto.setImagePath(imageUrl);
	            return ResponseEntity.ok(service.registerUser(createDto));

	        } catch (IOException e) { // Catch IOException from the service
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
	        } catch (Exception e) {
			System.out.println(e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    }
	
	 @PutMapping("/update-image")
	 public ResponseEntity<?> changeImage(@RequestParam("image") MultipartFile image, Long userId){
		 try {
			 String imageUrl = null;

	            if (image != null && !image.isEmpty()) {
	                imageUrl = awsService.uploadImage(image);
	            }
	            return ResponseEntity.ok(service.changeImage(userId, imageUrl));
		 } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	 }
	  
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto dto){
		try {
			return ResponseEntity.ok(service.updateUser(id, dto));
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
		System.out.println(loginDto);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
