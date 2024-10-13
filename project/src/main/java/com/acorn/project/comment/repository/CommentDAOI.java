package com.acorn.project.comment.repository;

import java.util.List;

import com.acorn.project.comment.domain.Comment;

public interface CommentDAOI {

	// 게시글에 따른 댓글 조회
	List<Comment> selectByBoard(String boardCode);
	
	// 댓글 수
	int count(String boardCode);

	// 등록
	int insert(Comment comment);

	// 수정
	int update(Comment comment);

	// 삭제
	int delete(String commentCode);
	
	// 문의 댓글
	Comment selectBoardIn(String boardCode);

}