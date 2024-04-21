package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService{
	
	UserRepository ur;
	public UserServiceImplementation(UserRepository ur) {
		super();
		this.ur = ur;
	}

	@Override
	public String addUser(Users user) {
		ur.save(user);
		return "User Saved Succesfully..!!";
	}

	@Override
	public boolean checkEmail(String email) {
		
		return ur.existsByEmail(email);
		
	}

//	@Override
//	public boolean validate(String email, String password) {
//		if(ur.existsByEmail(email)) {
//			Users u=ur.getByEmail(email);
//			String dbpasword=u.getPassword();
//			if(password.equals(dbpasword)) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//		else {
//			return false;
//		}
//	}
	@Override
	public boolean validateUser(String email, String password) {
		if(ur.existsByEmailAndPassword(email, password)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public Users getUserData(String email) {
		Users u=ur.getByEmail(email);
		return u;
	}

	@Override
	public String updateUser(Users user) {
		ur.save(user);
		return "student updated successfully!";
	}

}
