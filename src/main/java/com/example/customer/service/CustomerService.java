package com.example.customer.service;

import org.springframework.http.ResponseEntity;

import com.example.customer.entity.User;
import com.example.customer.entity.UserResponse;

public interface CustomerService {

	UserResponse registerUserInfo(User user);
	
	ResponseEntity<?>getUserDataByEmail(String Email);
	
}
