package com.example.customer.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.customer.dao.UserRepository;
import com.example.customer.entity.User;
import com.example.customer.entity.UserResponse;
import com.example.customer.enumValue.EnumData;
import com.example.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	private UserRepository userRepository;

	//	@Autowired
	//	private UserResponse userResponse;

	@Override
	public UserResponse registerUserInfo(User user) {
		System.out.println("In Customer Serivce Start");
		boolean isExist = getAlreadyRegisterdEmployeeData(user.getEmail());
		UserResponse userResponse = new UserResponse();
		if(isExist) {
			userResponse.setUsername(user.getEmail());
			userResponse.setMessage("User Email Already Exist.");
			return userResponse;
		}
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = formatter.format(date);
		user.setCreatedDate(strDate);
		boolean flag = false;
		EnumData status = EnumData.ACTIVE;
		if("A".equals(status.getValue())){
			flag = true;
		}
		user.setStatus(flag);
		user.setCreatedBy(user.getUsername());
		User user1 = userRepository.save(user);
		if(user1 != null) {
			userResponse.setUsername(user1.getUsername());
			userResponse.setMessage("Thank you ! User Successfully Created.");
		} else {
			userResponse.setMessage("Thank you ! User Not Successfully Created.");
		}
		System.out.println("In Customer Serivce End");
		return userResponse;
	}


	private boolean getAlreadyRegisterdEmployeeData(String email) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public ResponseEntity<?> getUserDataByEmail(String email)
	{
		System.out.println("In customer Service Getting user start");
		User user = userRepository.findByEmail(email);
		if(user != null)
		{
			new ResponseEntity<User>(user, HttpStatus.OK);
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(email);
		userResponse.setMessage("Email id done not exists.");
		System.out.println("In customer Service Getting user end");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);	
	}
	

}
