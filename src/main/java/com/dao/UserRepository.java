package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.User;
import com.model.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
    

	List<User> findByRole(UserRole admin);
	User findByUsernameAndPassword(String username, String password);

	 Optional<User> findByUsername(String username);
	
    Optional<User> findByEmail(String email);
}