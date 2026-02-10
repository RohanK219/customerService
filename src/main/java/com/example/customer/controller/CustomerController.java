package com.example.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entity.User;
import com.example.customer.entity.UserResponse;
import com.example.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(value = "/api/customer/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> registerUserInfo(@RequestBody User user){
		System.out.println("In Customer Controller Start.");
		UserResponse userResponse = customerService.registerUserInfo(user);
		System.out.println("In Customer Controller End.");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/api/customer/getuser/{emailId}")
	public ResponseEntity<?> getUserDataByEmail(@PathVariable("emailid") String email)
	{
		System.out.println("In Customer Controller Gatting user Start.");
		ResponseEntity<?> responseEntity = customerService.getUserDataByEmail(email);
		System.out.println("In Customer Controller Getting user End.");
		return responseEntity;
	}
}