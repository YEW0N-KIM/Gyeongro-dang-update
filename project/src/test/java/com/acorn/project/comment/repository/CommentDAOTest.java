package com.acorn.project.comment.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acorn.project.comment.domain.Comment;

@RunWith(MockitoJUnitRunner.class)
public class CommentDAOTest {

	@Mock
	SqlSession session;
	
	@InjectMocks
	CommentDAO dao;
	
	private String namespace = "com.acorn.commentMapper.";
	private Comment comment = new Comment();
	private String boardCode = "b0001";
	
	@Test
	public void testSelectByBoard() {

		when(session.selectList(namespace+"selectCode", boardCode)).thenReturn(Arrays.asList(comment));
		List<Comment> result = dao.selectByBoard(boardCode);
		assertEquals(1,result.size());
	}

	@Test
	public void testCount() {
		when(session.selectOne(namespace+"count",boardCode)).thenReturn(10);
		int result = dao.count(boardCode);
		assertEquals(10, result);
	}

	@Test
	public void testInsert() {
		when(session.insert(namespace+"insert",comment)).thenReturn(10);
		int result = dao.insert(comment);
		assertEquals(10, result);
	}

	@Test
	public void testUpdate() {
		when(session.update(namespace+"update",comment)).thenReturn(10);
		int result = dao.update(comment);
		assertEquals(10, result);
	}

	@Test
	public void testDelete() {
		String commentCode = "c0001";
		when(session.delete(namespace+"delete", commentCode)).thenReturn(10);
		int result = dao.delete(commentCode);
		assertEquals(10, result);
	}

	@Test
	public void testSelectBoardIn() {
		when(session.selectOne(namespace+"selectInquiry",boardCode)).thenReturn(comment);
		Comment result = dao.selectBoardIn(boardCode);
		assertEquals(comment, result);
	}

}
