package com.example.demo.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.service.TrainerService;




@Controller
@RequestMapping("/trainer")
public class TrainerController {
	
	TrainerService tservice;
	public TrainerController(TrainerService tservice) {
		this.tservice=tservice;
	}
	
	@PostMapping("/addCourse")
	public String addCourse(@RequestParam("courseId")int courseId,
			@RequestParam("courseName")String courseName,
			@RequestParam("coursePrice")int coursePrice) {
		
		Course course=new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setCoursePrice(coursePrice);
		
		Course c=tservice.addCourse(course);
		if(c!=null)
		{
		return "trainerHome";
		}
		else
		{
			return "/createCourseFail";
		}
	}
	@PostMapping("/lesson")
	public String lesson(@RequestParam("courseId")int courseId,
			@RequestParam("lessonId")int lessonId,
			@RequestParam("lessonName")String lessonName,
			@RequestParam("topics")String topics,
			@RequestParam("link")String link) {
		
      Course course=tservice.getCourse(courseId);
		
		Lesson lesson=new Lesson(lessonId,lessonName,topics,link,course);
		tservice.addLesson(lesson);
		
		course.getLessons().add(lesson);
		
		tservice.saveCourse(course);
		
		return "/trainerHome";
	}
	
	@GetMapping("/showCourses")
	public String showCourses(Model model) {
		List<Course> courseList=tservice.courseList();
		model.addAttribute("courseList",courseList);
	//	System.out.println(courseList);
		return "courses";
	}
	

}
