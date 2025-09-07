package com.service;

import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.dto.RegisterRequest;
import com.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(RegisterRequest request) {
    	  if (userRepository.findByUsername(request.getUsername()).isPresent()) {
              throw new RuntimeException("Username already exists");
          }

          User user = new User();
          user.setUsername(request.getUsername());
          user.setPassword(request.getPassword()); // store as plain text
          user.setEmail(request.getEmail());
          user.setRole(request.getRole());

          userRepository.save(user);
      }
  }

