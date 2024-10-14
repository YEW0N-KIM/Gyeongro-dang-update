package com.acorn.project.comment.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.comment.domain.Comment;
import com.acorn.project.comment.repository.CommentDAOI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class CommentServiceTest {
	
	@Autowired
	CommentDAOI dao;
	
	@Autowired
	CommentServiceI service;
	private Comment comment = new Comment("c0044","n0001", "b0006", "u0001", "댓글 예시", null);
	private Comment comment2 = new Comment("c0044","n0001", "b0006", "u0001", "댓글 예시22222", null);
	private String boardCode = "b0002"; 
	
	@Test
	public void testGetCommentByCode() {
		List<Comment> result = service.getCommentByCode(boardCode);
		List<Comment> check = dao.selectByBoard(boardCode);
		assertEquals(check,result);
	}

	@Test
	public void testCount() {
		int result = dao.count(boardCode);
		int check = service.count(boardCode);
		assertEquals(check, result);
	}

	@Test
	public void testRegister() {
		int result = service.register(comment);
		assertEquals(1, result);
	}
	
	@Test
	public void testModify() {
		int result = service.modify(comment2);
//		assertEquals(1, result);
	}

	@Test
	public void testDelete() {
		int result = service.delete("c0044");
//		assertEquals(1, result);
	}

	@Test
	public void testSelectBoardIn() {
		Comment result = service.selectBoardIn(boardCode);
		Comment check = dao.selectBoardIn(boardCode);
		assertEquals(check,result);
	}

}
