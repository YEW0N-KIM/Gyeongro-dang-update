package com.acorn.project.point.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.point.domain.Point;
import com.acorn.project.point.repository.PointDAOI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class PointServiceTest {

	@Autowired
	PointDAOI dao;
	
	@Autowired
	PointServiceI service;
	
	private String userCode = "u0001";
	
	
	@Test
	public void testCount() {
		int check = dao.count();
		int result = service.count();
		assertEquals(check,result);
	}

	@Test
	public void testGetListAll() throws Exception{
		List<Point> check = dao.selectAll();
		List<Point> result = service.getListAll();
		assertEquals(check, result);
	}

	@Test
	public void testGetPointOne() throws Exception{
		List<Map<String,Object>> check = dao.selectOne(userCode);
		List<Map<String,Object>> result = service.getPointOne(userCode);
		assertEquals(check, result);
	}

	@Test
	public void testBuyBoard() throws Exception{
		int result = service.buyBoard(userCode, "b0001", 0);
		assertEquals(1, result);
	}

	@Test
	public void testSellBoard() throws Exception{
		int result = service.sellBoard(userCode, "b0001", 0);
		assertEquals(1, result);
	}

	@Test
	public void testBuyPoint() throws Exception{
		int result = service.buyPoint(0, userCode);
		assertEquals(1, result);
	}

	@Test
	public void testPointExchange() throws Exception{
		int result = service.pointExchange(0, userCode);
		assertEquals(1, result);
	}

	@Test
	public void testGetPointsWithinDateRange() throws Exception{
		List<Map<String,Object>> check = dao.selectPointsWithinDateRange(userCode, "2024-05-31", "2024-06-07");
		List<Map<String,Object>> result = service.getPointsWithinDateRange(userCode, "2024-05-31", "2024-06-07");
		assertEquals(check,result);
	}

}
