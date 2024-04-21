package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Comments;

public interface CommentService {
	public List<Comments> commentList();
	public String addComment(Comments comment);
}
