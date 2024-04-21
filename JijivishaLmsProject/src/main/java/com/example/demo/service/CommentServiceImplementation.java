package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Comments;
import com.example.demo.repository.CommentRepository;
@Service
public class CommentServiceImplementation implements CommentService {
	public CommentServiceImplementation(CommentRepository cr) {
		super();
		this.cr = cr;
	}

	CommentRepository cr;

	@Override
	public List<Comments> commentList() {
		return cr.findAll();
	}

	@Override
	public String addComment(Comments comment) {
		cr.save(comment);
		return "comment added";
	}

}
