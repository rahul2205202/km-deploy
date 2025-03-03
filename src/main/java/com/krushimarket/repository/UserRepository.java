package com.krushimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krushimarket.model.User;
import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	Optional<User> findByPhone(String phone);
}
