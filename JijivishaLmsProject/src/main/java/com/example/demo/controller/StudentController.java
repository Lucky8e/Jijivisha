package com.example.demo.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.Users;
import com.example.demo.service.CommentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TrainerService;
import com.example.demo.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	UserService us;
	TrainerService ts;
	StudentService ss;
	CommentService cs;
	
public StudentController(UserService us, TrainerService ts, StudentService ss, CommentService cs) {
		super();
		this.us = us;
		this.ts = ts;
		this.ss = ss;
		this.cs = cs;
	}

@GetMapping("/fetchCourses")
public String fetchCourses(Model model, HttpSession session) {
	
		Users loggedUser=(Users) session.getAttribute("loggedInUser");
		String email=loggedUser.getEmail();		
		Users user=us.getUserData(email);	
		List<Course> courseList=user.getCourses();
		model.addAttribute("courseList",courseList);
		return "myCourses";
	}

@GetMapping("/viewLesson")
public String viewLesson(@RequestParam("lessonId")int lessonId,
						Model model,HttpSession session) {
//	Users user = (Users) session.getAttribute("loggedInUser");
	Lesson lesson = ss.getLesson(lessonId);
	// Extract the YouTube video id from the URL
    String youtubeUrl = lesson.getLink();
    
    String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=") + 1);
    lesson.setLink(videoId);
	
	
	model.addAttribute("lesson",lesson);
	List<Comments> commentsList=cs.commentList();
	model.addAttribute("comments",commentsList);
	
	return "myLesson";
}
@PostMapping("/addComment")
public String comments(@RequestParam("comment")String comment
					,Model model) {
	Comments c=new Comments();
	c.setComment(comment);
	cs.addComment(c);
	
	List<Comments> commentsList=cs.commentList();
	model.addAttribute("comments",commentsList);
	
	return "myLesson";
}
@GetMapping("/purchase")
public String showCourses(Model model,HttpSession session) {
	Users user = (Users) session.getAttribute("loggedInUser");

	List<Course> courseList=ts.courseList();
	model.addAttribute("courseList",courseList);
	model.addAttribute("loggedInUser",user);
	return "purchase";
}

}
