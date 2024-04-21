package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.UserRepository;
@Service
public class StudentServiceImplementation implements StudentService {
   UserRepository ur;
   LessonRepository lr;
	public StudentServiceImplementation(UserRepository ur, LessonRepository lr) {
	super();
	this.ur = ur;
	this.lr = lr;
}
	@Override
	public Lesson getLesson(int lessonId) {
		return lr.findById(lessonId).get();
	}

}
