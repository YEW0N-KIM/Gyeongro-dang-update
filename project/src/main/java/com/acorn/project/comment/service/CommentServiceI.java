package com.acorn.project.comment.service;

import java.util.List;

import com.acorn.project.comment.domain.Comment;

public interface CommentServiceI {

	List<Comment> getCommentByCode(String code);
	
	int count(String boardCode);

	int register(Comment comment);

	int modify(Comment comment);

	int delete(String code);
	
	Comment selectBoardIn(String boardCode);

}