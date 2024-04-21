package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.LessonRepository;
@Service
public class TrainerServiceImpl implements TrainerService {

	CourseRepository crepo;
	LessonRepository lrepo;
	public TrainerServiceImpl(CourseRepository crepo, LessonRepository lrepo) {
		super();
		this.crepo = crepo;
		this.lrepo=lrepo;
	}

	@Override
	public Course addCourse(Course course) {
		System.out.println("Course Added Succesfully");
		return crepo.save(course);
		
	}

	@Override
	public String saveCourse(Course course) {
		crepo.save(course);
		return "Course saved successfully!";
	}

	@Override
	public String addLesson(Lesson lesson) {
		lrepo.save(lesson);
		return "lesson added successfully!";
	}

	@Override
	public Course getCourse(int courseId) {
		return crepo.findById(courseId).get();
	}

	@Override
	public List<Course> courseList() {
		return crepo.findAll();
	}

}
