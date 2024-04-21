package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nav")
public class NavigationController {
	UserService uService;
	
	public NavigationController(UserService uService) {
		super();
		this.uService = uService;
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/registerFail")
	public String registerFail() {
		return "registerFail";
	}
	              
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/adduser")
	public String addUser(@RequestParam("name")String name,@RequestParam("email")String email,
			              @RequestParam("password")String password,@RequestParam("role")String role) {
		boolean b=uService.checkEmail(email);
		if(b==false) {
			Users user=new Users();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);
			uService.addUser(user);
			System.out.println("User Saved Successfully...!!!");
			return "redirect:/nav/login";
			
		}else {
			System.out.println("User Already Exists..!!!");
			return "redirect:/nav/registerFail";
			
		}
	
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,@RequestParam("password")String password,HttpSession session) {
		if(uService.validateUser(email, password)) {
			Users user=uService.getUserData(email);
			session.setAttribute("loggedInUser", user);
			if(user.getRole().equals("trainer")) {
				return "trainerHome";
			}else {
				return "studentHome";
			}
		}else {
			System.out.println("Login Credentials Are Incorect Pleasae try Again");
			return "loginFail";
		}                                                                                                                                                                     
	}
	@GetMapping("/createCourse")
	public String createCourses() {
		return "createCourse";
	}
	@GetMapping("/addLesson")
	public String addLesson() {
		return "addLesson";
	}
	
	
	

}
