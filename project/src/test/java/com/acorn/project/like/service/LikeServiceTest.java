package com.acorn.project.like.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.like.domain.Like;
import com.acorn.project.like.repository.LikeDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class LikeServiceTest {
	
	@Autowired
	LikeDAO dao;
	
	@Autowired
	LikeService service;

	private Like like = new Like("u0001", "b0001");
	
	@Test
	public void testIncrLike() {
		int result = service.incrLike(like);
		assertEquals(1, result);
	}

	@Test
	public void testDecrLike() {
		int result = service.decrLike(like);
		assertEquals(1, result);
	}

	@Test
	public void testCheckLike() {
		Like result = service.checkLike(like);
		Like check = dao.selectOne(like);
		assertEquals(check, result);
	}

}
