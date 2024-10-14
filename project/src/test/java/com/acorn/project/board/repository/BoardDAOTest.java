package com.acorn.project.board.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
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

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;

@RunWith(MockitoJUnitRunner.class)
public class BoardDAOTest {
	
	@Mock
	private SqlSession session;
	
	@InjectMocks
	private BoardDAO dao;
	
	private String namespace = "com.acorn.boardMapper.";
	private Board mock = new Board();
	private RouteBoard Rmock = new RouteBoard();

	@Test
	public void testSelectTotalCount() {
		int boardType = 0;
		when(session.selectOne(namespace+"selectTotalCount", boardType)).thenReturn(10);
		
		int result = dao.selectTotalCount(boardType);
		assertEquals(10, result);
		verify(session).selectOne(namespace+"selectTotalCount", boardType);
	}

	@Test
	public void testSelectAll() {
		int boardType = 0;
		int currentPage = 1;
		int  pageSize  =   15;		
		int offset = (currentPage - 1) * pageSize;  
	 	
		HashMap<String, Integer> info = new HashMap<>();
		info.put("boardType",  boardType);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace+"selectAll", info)).thenReturn(Arrays.asList(mock));
		
		List<Board> result = dao.selectAll(boardType, currentPage);
		assertNotNull(result);
		assertEquals(1,result.size());
		verify(session).selectList(namespace+"selectAll", info);
	}
	
	

	@Test
	public void testSelectRouteAll() {
		int boardType = 0;
		int currentPage = 1;
		int  pageSize  =   5;		
		int offset = (currentPage - 1) * pageSize;  
	 	
		HashMap<String, Integer> info = new HashMap<>();
		info.put("boardType",  boardType);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		
		when(session.selectList(namespace+"selectRouteAll", info)).thenReturn(Arrays.asList(Rmock));
		
		List<RouteBoard> result = dao.selectRouteAll(boardType, currentPage);
		assertNotNull(result);
		assertEquals(1,result.size());
		verify(session).selectList(namespace+"selectRouteAll", info);
	}

	@Test
	public void testSelectOne() {
		String board_code = "b0005";
		when(session.selectOne(namespace+"selectOne", board_code)).thenReturn(mock);
		
		Board result =  dao.selectOne(board_code);
		
		assertNotNull(mock);
		assertEquals(mock, result);
		verify(session).selectOne(namespace+ "selectOne", board_code);
		
	}

	@Test
	public void testUpdateViews() {
		String boardCode = "b0001";
		when(session.update(namespace+"updateView", boardCode)).thenReturn(10);
		int result = dao.updateViews(boardCode);
		
		assertEquals(10, result);
		verify(session).update(namespace+"updateView",boardCode);
	}

	@Test
	public void testSelectTheme() {
		String boardTheme = "0";
		when(session.selectList(namespace+"selectTheme",boardTheme)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectTheme(boardTheme);
		
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectTheme", boardTheme);
		
	}

	@Test
	public void testSelectUserCount() {
		String user_id = "1";
		when(session.selectOne(namespace+"selectMyCount", user_id)).thenReturn(10);
		int result = dao.selectUserCount(user_id);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace+"selectMyCount", user_id);
	}

	@Test
	public void testSelectUser() {
		String userId = "1";
		int currentPage = 1;
		
		 //현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new HashMap();
		info.put("userId", userId);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace+"selectMy", info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectUser(userId, currentPage);
		
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMy", info);
	}

	@Test
	public void testMyArchCount() {
		String userId = "1";
		when(session.selectOne(namespace+"MyArchCount", userId)).thenReturn(10);
		int result = dao.MyArchCount(userId);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace+"MyArchCount", userId);
	}

	@Test
	public void testSelectUserArch() {
		String userId = "1";
		int currentPage = 1;
		int pageSize = 10;
		int offset = (currentPage - 1) * pageSize;
		
		Map<String, Object> info = new HashMap();
		info.put("userId", userId);
		info.put("offset", offset);
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace + "selectMyarchive",info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectUserArch(userId, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMyarchive", info);
	}

	@Test
	public void testMyLikeCount() {
		String userId = "1";
		when(session.selectOne(namespace+"MyLikeCount", userId)).thenReturn(10);
		int result = dao.MyLikeCount(userId);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace + "MyLikeCount", userId);
	}

	@Test
	public void testSelectUserLike() {
		String userId = "1";
		int currentPage = 1;
		int  pageSize  =   10;		
		int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new HashMap();
		info.put("userId", userId);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace + "selectMylike",info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectUserLike(userId, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMylike", info);
	}

	@Test
	public void testMyComCount() {
		String userId = "1";
		when(session.selectOne(namespace+"MyComCount", userId)).thenReturn(10);
		int result = dao.MyComCount(userId);
		assertEquals(10, result);
		verify(session).selectOne(namespace+"MyComCount", userId);
	}

	@Test
	public void testSelectUserCom() {
		String userId = "1";
		int currentPage = 1;
		int  pageSize  =   10;		
		int offset = (currentPage - 1) * pageSize;  
		
		Map<String, Object> info = new HashMap();
		info.put("userId", userId);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace+"selectMycom", info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectUserCom(userId, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMycom", info);
	}

	

	@Test
	public void testMyPointCount() {
		String userId = "1";
		when(session.selectOne(namespace+"MyPointCount", userId)).thenReturn(10);
		int result = dao.MyPointCount(userId);
		assertEquals(10, result);
		verify(session).selectOne(namespace+"MyPointCount", userId);
	}

	@Test
	public void testSelectUserPoint() {
		String userId = "1";
		int currentPage = 1;
		int  pageSize  =   10;		
		int offset = (currentPage - 1) * pageSize;  
		
		Map<String, Object> info = new HashMap();
		info.put("userId", userId);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace+"selectMypoint", info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectUserPoint(userId, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMypoint", info);
	}

	@Test
	public void testSelectMyinqu() {
		String userId = "1";
		when(session.selectList(namespace+"selectMyinqu", userId)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectMyinqu(userId);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectMyinqu", userId);
	}

	@Test
	public void testInsertBoard() {
		when(session.insert(namespace + "insert", mock)).thenReturn(1);
		int result = dao.insertBoard(mock);
		assertEquals(1, result);
		verify(session).insert(namespace + "insert", mock);
	}

	@Test
	public void testUpdateBoard() {
		when(session.update(namespace + "update", mock)).thenReturn(1);
		int result = dao.updateBoard(mock);
		assertEquals(1, result);
		verify(session).update(namespace + "update", mock);
	}

	@Test
	public void testDeleteBoard() {
		String board_code = "b0001";
		when(session.delete(namespace + "delete", board_code)).thenReturn(1);
		int result = dao.deleteBoard(board_code);
		assertEquals(1, result);
		verify(session).delete(namespace + "delete", board_code);
	}

	@Test
	public void testGetList() {
		SearchCondition search = new SearchCondition();
		int currentPage = 1;
		int pageSize=15;	
		int  start  =   (currentPage  -1) *pageSize;
	 	search.setStart(start);
	 	
	 	when(session.selectList(namespace+"selectSearch",search)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.getList(search, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectSearch",search);
	}

	@Test
	public void testGetRouteList() {
		SearchCondition search = new SearchCondition();
		int currentPage = 1;
		int pageSize=15;	
		int  start  =   (currentPage  -1) *pageSize;
	 	search.setStart(start);
	 	
	 	when(session.selectList(namespace+"selectRouteSearch",search)).thenReturn(Arrays.asList(Rmock));
		List<RouteBoard> result = dao.getRouteList(search, currentPage);
		assertEquals(1, result.size());
		verify(session).selectList(namespace+"selectRouteSearch",search);
	}

	@Test
	public void testGetListCount() {
		SearchCondition search = new SearchCondition();
		when(session.selectOne(namespace+"selectSearchCount", search)).thenReturn(10);
		int result = dao.getListCount(search);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace+"selectSearchCount", search);
	}

	@Test
	public void testGetRouteListCount() {
		SearchCondition search = new SearchCondition();
		when(session.selectOne(namespace+"selectSearchRouteCount", search)).thenReturn(10);
		int result = dao.getRouteListCount(search);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace+"selectSearchRouteCount", search);
	}

	@Test
	public void testSelectRoute() {
		String boardCode = "b0001";
		when(session.selectOne(namespace+"selectRoute", boardCode)).thenReturn(Rmock);
		RouteBoard result = dao.selectRoute(boardCode);
		
		assertEquals(Rmock, result);
		verify(session).selectOne(namespace+"selectRoute", boardCode);
	}

	@Test
	public void testInsertRoute() {
		when(session.insert(namespace + "insertRoute", Rmock)).thenReturn(1);
		int result = dao.insertRoute(Rmock);
		assertEquals(1, result);
		verify(session).insert(namespace + "insertRoute", Rmock);
	}

	@Test
	public void testSelectMyBuyBoard() {
		String userCode = "u0001";
		when(session.selectList(namespace + "selectMyBuyBoard", userCode)).thenReturn(Arrays.asList(Rmock));
		List<String> result = dao.selectMyBuyBoard(userCode);
		
		assertEquals(1, result.size());
		verify(session).selectList(namespace + "selectMyBuyBoard", userCode);
	}

	@Test
	public void testLikeCount() {
		String boardCode = "b0001";
		when(session.selectOne(namespace + "LikeCount", boardCode)).thenReturn(10);
		int result = dao.likeCount(boardCode);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace + "LikeCount", boardCode);
	}

	@Test
	public void testArchCount() {
		String boardCode = "b0001";
		when(session.selectOne(namespace + "ArchCount", boardCode)).thenReturn(10);
		int result = dao.archCount(boardCode);
		
		assertEquals(10, result);
		verify(session).selectOne(namespace + "ArchCount", boardCode);
	}

	@Test
	public void testGetRouteBoardBySearch() {
		  int currentPage = 1;
		  String region = "서울";
		  String theme = "-1";
		  String tourdays = "1";
		  int pageSize = 5;
		  int offset = (currentPage - 1) * pageSize;
		    
		  Map<String, Object> info = new HashMap<>();
		  info.put("region", region);
		  info.put("theme", theme);
		  info.put("tourdays", tourdays);
		  info.put("offset", offset);
		  info.put("pageSize", pageSize);
		  
		  when(session.selectList(namespace + "getRouteBoardBySearch", info)).thenReturn(Arrays.asList(Rmock));
		  List<RouteBoard> result = dao.getRouteBoardBySearch(region, theme, tourdays, currentPage);
		  assertEquals(1, result.size());
		  verify(session).selectList(namespace + "getRouteBoardBySearch", info);
	}

	@Test
	public void testGetTotalCountBySearch() {
		 String region = "서울";
		 String theme = "-1";
		 String tourdays = "1";
		 Map<String, Object> info = new HashMap<>();
	     info.put("region", region);
	     info.put("theme", theme);
	     info.put("tourdays", tourdays);
	     
	     when(session.selectOne(namespace + "getTotalCountBySearch", info)).thenReturn(10);
		 int result = dao.getTotalCountBySearch(region, theme, tourdays);
		 assertEquals(10, result);
		 verify(session).selectOne(namespace + "getTotalCountBySearch", info);
	}

	@Test
	public void testHomeRouteData() {
		int boardRegion = 0;
		when(session.selectList(namespace + "homeRouteData", boardRegion)).thenReturn(Arrays.asList(mock));
		List<RouteBoard> result = dao.homeRouteData(boardRegion);
		
		assertEquals(1, result.size());
		verify(session).selectList(namespace + "homeRouteData", boardRegion);
	}

	@Test
	public void testSelectInquiryCount() {
		when(session.selectOne(namespace+"selectInquiryCount")).thenReturn(10);
		int result = dao.selectInquiryCount();
		
		assertEquals(10, result);
		verify(session).selectOne(namespace+"selectInquiryCount");
	}

	@Test
	public void testSelectInquiry() {
		
	    int currentPage = 1;      
	  	int  pageSize  =   10;		
		int offset = (currentPage - 1) * pageSize;  
	 	
		Map info = new  HashMap();
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		when(session.selectList(namespace+"selectInquiry", info)).thenReturn(Arrays.asList(mock));
		List<Board> result = dao.selectInquiry(currentPage);
		
		assertEquals(1, result.size());
	}
  }
