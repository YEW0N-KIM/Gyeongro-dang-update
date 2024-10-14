package com.acorn.project.point.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acorn.project.point.domain.Point;

@RunWith(MockitoJUnitRunner.class)
public class PointDAOTest {

	@Mock
	SqlSession session;
	
	@InjectMocks
	PointDAO dao;
	
	private String namespace = "com.acorn.PointMapper.";
	private Point point = new Point();
	private String userCode = "u0001";
	
	@Test
	public void testCount() {
		when(session.selectOne(namespace+"count")).thenReturn(10);
		int result = dao.count();
		assertEquals(10,result);
	}

	@Test
	public void testSelectAll() throws Exception{
		when(session.selectList(namespace+"selectAll")).thenReturn(Arrays.asList(point));
		List<Point> result = dao.selectAll();
		assertEquals(1, result.size());
	}

	@Test
	public void testSelectOne() throws Exception{
		when(session.selectList(namespace+"selectOne", userCode)).thenReturn(Arrays.asList(point));
		List<Map<String, Object>> result = dao.selectOne(userCode);
		assertEquals(1, result.size());
	}

	@Test
	public void testBuyBoard() throws Exception{
		String boardCode="b0001"; int pointAmount=0;
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userCode", userCode);
        paramMap.put("boardCode", boardCode);
        paramMap.put("pointAmount", pointAmount);
		when(session.insert(namespace+"buyBoard",paramMap)).thenReturn(10);
		int result = dao.buyBoard(userCode, boardCode, pointAmount);
		assertEquals(10, result);
	}

	@Test
	public void testBuyPoint() throws Exception{
		int pointAmount=0;
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pointAmount", pointAmount);
        paramMap.put("userCode", userCode);
        when(session.insert(namespace+"buyPoint", paramMap)).thenReturn(10);
        int result = dao.buyPoint(pointAmount, userCode);
        assertEquals(10, result);
	}

	@Test
	public void testSellBoard() throws Exception{
		String boardCode="b0001"; int pointAmount=0;
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userCode", userCode);
        paramMap.put("boardCode", boardCode);
        paramMap.put("pointAmount", pointAmount);
        when(session.insert(namespace+"sellBoard", paramMap)).thenReturn(10);
        int result = dao.sellBoard(userCode, boardCode, pointAmount);
        assertEquals(10, result);
	}

	@Test
	public void testPointExchange() throws Exception{
		int pointAmount=0;
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pointAmount", pointAmount);
        paramMap.put("userCode", userCode);
        when(session.insert(namespace+"pointExchange", paramMap)).thenReturn(10);
        int result = dao.pointExchange(pointAmount, userCode);
        assertEquals(10, result);
	}

	@Test
	public void testSelectPointsWithinDateRange() throws Exception{
		String startDate = "2024-05-31"; String endDate = "2024-05-31";
		Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userCode", userCode);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        when(session.selectList(namespace+"selectPointsWithinDateRange", paramMap)).thenReturn(Arrays.asList(point));
        List<Map<String, Object>> result = dao.selectPointsWithinDateRange(userCode, startDate, endDate);
        assertEquals(1, result.size());
	}

}
