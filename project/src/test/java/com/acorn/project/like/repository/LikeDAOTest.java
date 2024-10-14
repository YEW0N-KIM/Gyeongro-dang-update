package com.acorn.project.like.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acorn.project.like.domain.Like;

@RunWith(MockitoJUnitRunner.class)
public class LikeDAOTest {
	
	@Mock
	SqlSession session;
	
	@InjectMocks
	LikeDAO dao;
	
	private String namespace = "com.acorn.likeMapper.";
	private Like like = new Like();
	
	@Test
	public void testInsertLike() {
		when(session.insert(namespace+"incrLike", like)).thenReturn(10);
		int result = dao.insertLike(like);
		assertEquals(10, result);
	}

	@Test
	public void testDeleteLike() {
		when(session.delete(namespace+"decrLike", like)).thenReturn(10);
		int result = dao.deleteLike(like);
		assertEquals(10, result);
	}

	@Test
	public void testSelectOne() {
		when(session.selectOne(namespace+"selectOne", like)).thenReturn(like);
		Like result = dao.selectOne(like);
		assertEquals(like, result);
	}

}
