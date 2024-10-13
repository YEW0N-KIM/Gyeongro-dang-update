package com.acorn.project.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;
import com.acorn.project.board.repository.BoardDAOI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class BoardServiceTest {
	
	@Autowired
	BoardServiceI service;
	
	@Autowired
	BoardDAOI dao;
	
//	@Test
	public void testSelectTotalCount() {
		int result = service.selectTotalCount(-1);
		int check = dao.selectTotalCount(-1);
		assertEquals(check, result);
	}

//	@Test
	public void testGetBoardBytype() {
		List<Board> result = service.getBoardBytype(-1, 1);
		List<Board> check = dao.selectAll(-1, 1);
		assertEquals(check, result);
		
	}

//	@Test
	public void testGetRouteBoard() {
		List<RouteBoard> result = service.getRouteBoard(0, 1);
		List<RouteBoard> check = dao.selectRouteAll(0, 1);
		assertEquals(check, result);
	}

//	@Test
	public void testGetBoardBycode() {
		Board result = service.getBoardBycode("b0001");
		Board check = dao.selectOne("b0001");
		assertEquals(check, result);
	}

//	@Test
	public void testUpdateViews() {
		int result = service.updateViews("b0001");
		assertEquals(1, result);
	}

//	@Test
	public void testGetBoardBytheme() {
		List<Board> result = service.getBoardBytheme("0");
		List<Board> check = dao.selectTheme("0");
		assertEquals(check, result);
	}

//	@Test
	public void testSelectUserCount() {
		int result = service.selectUserCount("1");
		int check = dao.selectUserCount("1");
		assertEquals(check, result);
	}

//	@Test
	public void testGetBoardByuser() {
		List<Board> check = dao.selectUser("1", 1);
		List<Board> result = service.getBoardByuser("1", 1);
		assertEquals(check, result);
	}

//	@Test
	public void testMyArchCount() {
		int result = service.MyArchCount("1");
		int check = dao.MyArchCount("1");
		assertEquals(check, result);
	}

//	@Test
	public void testSelectUserArch() {
		List<Board> result = service.selectUserArch("1", 1);
		List<Board> check = dao.selectUserArch("1", 1);
		assertEquals(check,result);
	}

//	@Test
	public void testMyLikeCount() {
		int result = service.MyLikeCount("1");
		int check = dao.MyLikeCount("1");
		assertEquals(check, result);
	}

//	@Test
	public void testSelectUserLike() {
		List<Board> result = service.selectUserLike("1", 1);
		List<Board> check = dao.selectUserLike("1", 1);
		assertEquals(check, result);
	}

//	@Test
	public void testMyComCount() {
		int result = service.MyComCount("1");
		int check = dao.MyComCount("1");
		assertEquals(check, result);
	}

//	@Test
	public void testSelectUserCom() {
		List<Board> result = service.selectUserCom("1", 1);
		List<Board> check = dao.selectUserCom("1", 1);
		assertEquals(check, result);
	}

//	@Test
	public void testMyPointCount() {
		int result = service.MyPointCount("1");
		int check = dao.MyPointCount("1");
		assertEquals(check, result);
	}

//	@Test
	public void testSelectUserPoint() {
		List<Board> result = service.selectUserPoint("1", 1);
		List<Board> check = dao.selectUserPoint("1", 1);
		assertEquals(check, result);
	}

//	@Test
	public void testGetInquByuser() {
		List<Board> result = service.getInquByuser("1");
		List<Board> check = dao.selectMyinqu("1");
		assertEquals(check, result);
	}

//	@Test
	public void testRegBoard() {
		Board board = new Board(null,"u0001","n0001",null,null,"예시22","예시~~~~~~~~~",0,0,null,0,0,1,0);
		int result = service.regBoard(board);
		assertEquals(1, result);
	}

//	@Test
	public void testModiBoard() {
		Board board = new Board(null,"u0001","n0001",null,null,"예시","예시2~~~~~~~~~",0,0,null,0,0,1,0);
		int result = service.regBoard(board);
		assertEquals(1, result);
	}

//	@Test
	public void testDelBoard() {
		int result = service.delBoard("b0041");
		assertEquals(1, result);
	}

//	@Test
	public void testGetList() {
		SearchCondition search = new SearchCondition("title","예시",1);
		List<Board> result = service.getList(search, 1);
		List<Board> check = dao.getList(search, 1);
		assertEquals(check, result);
		
	}

//	@Test
	public void testGetRouteList() {
		SearchCondition search = new SearchCondition("writer","n0001",1);
		List<RouteBoard> result = service.getRouteList(search, 1);
		List<RouteBoard> check = dao.getRouteList(search, 1);
		assertEquals(check,result);
	}

//	@Test
	public void testGetListCount() {
		SearchCondition search = new SearchCondition("title","예시",1);
		int result = service.getListCount(search);
		int check = dao.getListCount(search);
		assertEquals(check, result);
	}

//	@Test
	public void testGetRouteListCount() {
		SearchCondition search = new SearchCondition("writer","n0001",1);
		int result = service.getRouteListCount(search);
		int check = dao.getRouteListCount(search);
		assertEquals(check, result);
	}

//	@Test
	public void testSelectRoute() throws Exception {
		RouteBoard result = service.selectRoute("b0038");
		RouteBoard check = dao.selectRoute("b0038");
		assertEquals(check, result);
	}

//	@Test
//	public void testInsertRoute() {
//		RouteBoard route = new RouteBoard();
//	}

//	@Test
	public void testSelectMyBuyBoard() {
		List<String> result = service.selectMyBuyBoard("u0001");
		List<String> check = dao.selectMyBuyBoard("u0001");
		assertEquals(check,result);
	}

//	@Test
	public void testLikeCount() {
		int result = service.likeCount("b0001");
		int check = dao.likeCount("b0001");
		assertEquals(check, result);
	}

//	@Test
	public void testArchCount() {
		int result = service.archCount("b0001");
		int check = dao.archCount("b0001");
		assertEquals(check, result);
	}

//	@Test
	public void testGetRouteBoardBySearch() {
		List<RouteBoard> result = service.getRouteBoardBySearch("5", "1", "0", 1);
		List<RouteBoard> check = dao.getRouteBoardBySearch("5", "1", "0", 1);
		assertEquals(check, result);
	}

//	@Test
	public void testGetTotalCountBySearch() {
		int result = service.getTotalCountBySearch("5", "1", "0");
		int check = dao.getTotalCountBySearch("5", "1", "0");
		assertEquals(check, result);
	}

//	@Test
	public void testGetHomeRouteData() {
		List<RouteBoard> result = service.getHomeRouteData(5);
		List<RouteBoard> check = dao.homeRouteData(5);
		assertEquals(check, result);
	}

//	@Test
	public void testSelectInquiryCount() {
		int result = service.selectInquiryCount();
		int check = dao.selectInquiryCount();
		assertEquals(check, result);
	}

//	@Test
	public void testSelectInquiry() {
		List<Board> result = service.selectInquiry(1);
		List<Board> check = dao.selectInquiry(1);
		assertEquals(check, result);
	}

}
