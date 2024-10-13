package com.acorn.project.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.comment.domain.Comment;
import com.acorn.project.comment.repository.CommentDAOI;

@Repository
public class CommentService implements CommentServiceI {
	
	@Autowired
	CommentDAOI dao;
	
	@Override
	public List<Comment> getCommentByCode(String code){
		return dao.selectByBoard(code);
	}
	
	@Override
	public int count(String boardCode) {
		return dao.count(boardCode);
	}
	
	@Override
	public int register(Comment comment) {
		return dao.insert(comment);
	}
	
	@Override
	public int modify(Comment comment) {
		return dao.update(comment);
	}
	
	@Override
	public int delete(String code) {
		return dao.delete(code);
	}
	
	// 문의 댓글
	@Override
	public Comment selectBoardIn(String boardCode) {
		return dao.selectBoardIn(boardCode);
	}

	
}
