package com.acorn.project.comment.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.comment.domain.Comment;

@Repository
public class CommentDAO implements CommentDAOI {
	
	@Autowired
	private SqlSession session;
	private String namespace="com.acorn.commentMapper.";
	
	// 게시글에 따른 댓글 조회
	@Override
	public List<Comment> selectByBoard(String boardCode){
		return session.selectList(namespace+"selectCode",boardCode);
	}
	
	// 댓글 수
	@Override
	public int count(String boardCode) {
		return session.selectOne(namespace+"count",boardCode);
	}
	
	// 등록
	@Override
	public int insert(Comment comment) {
		return session.insert(namespace+"insert",comment);
	}
	
	// 수정
	@Override
	public int update(Comment comment) {
		return session.update(namespace+"update",comment);
	}
	
	// 삭제
	@Override
	public int delete(String commentCode) {
		return session.delete(namespace+"delete",commentCode);
	}
	
	// 문의 댓글
	@Override
	public Comment selectBoardIn(String boardCode) {
		return session.selectOne(namespace+"selectInquiry",boardCode);
	}
}
