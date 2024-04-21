package com.example.demo.service;

import com.example.demo.entity.Users;

public interface UserService {
	
	
	//adds new user to database
	public String addUser (Users user);
	//checks email is already present in database or not
	public boolean checkEmail (String email);
	//validates user's credentials
	public boolean validateUser(String email,String password);
	//Get Users Data
	public Users getUserData(String email);
	//save updated user to database
	public String updateUser(Users user);
		
}
