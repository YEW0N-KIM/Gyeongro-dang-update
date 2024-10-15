package com.acorn.project.board.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.acorn.project.archive.domain.Archive;
import com.acorn.project.archive.service.ArchiveServiceI;
import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.BoardVO;
import com.acorn.project.board.domain.Day;
import com.acorn.project.board.domain.PagingHandler;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.RouteBoardVO;
import com.acorn.project.board.domain.SearchCondition;
import com.acorn.project.board.service.BoardServiceI;
import com.acorn.project.comment.domain.Comment;
import com.acorn.project.comment.service.CommentServiceI;
import com.acorn.project.like.domain.Like;
import com.acorn.project.like.service.LikeServiceI;
import com.acorn.project.report.domain.Report;
import com.acorn.project.report.service.ReportServiceI;
import com.acorn.project.user.domain.User;
import com.acorn.project.user.service.UserServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   // Servlet의 ServletContext를 이용하기 위함
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {

	
	private MockMvc mvc;
	
	@Mock
	private BoardServiceI boardS;
	
	@Mock
	private UserServiceI userS;
	
	@Mock
	private CommentServiceI commentS;
	
	@Mock
	private LikeServiceI likeS;
	
	@Mock
	private ReportServiceI reportS;
	
	@Mock
	private ArchiveServiceI archS;
	
	@Mock
    private HttpSession session;

    @Mock
    private Model model;
    
    @Mock
    private HttpServletRequest request;
	
	@InjectMocks
	private BoardController control;
	
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	private PagingHandler handler ; 
	private SearchCondition search;
	private User user;
	private Board board;
	private Comment comment;
	
	@Test
	public void testBoardListType() throws Exception {
		
		handler = new PagingHandler(1,100,15);
		
	    List<Board> mockBoardList = new ArrayList<>();
	    when(boardS.getBoardBytype(1, 1)).thenReturn(mockBoardList);
	    when(boardS.selectTotalCount(1)).thenReturn(100); // 총 개수 설정
	    
	    String result = control.BoardListType("1", model, 1);
	    
	    assertEquals("board/freeboardList", result);
	    verify(model).addAttribute("freeBoardList", mockBoardList);
	    verify(model).addAttribute("type", 1);
	    verify(model).addAttribute("paging", handler);
	}

	@Test
	public void testSearchboard() {
		
		handler = new PagingHandler(1,100,15);
		search = new SearchCondition("title", "예시", 1);
		
		List<Board> mockList = new ArrayList<>();
		when(boardS.getList(search, 1)).thenReturn(mockList);
		when(boardS.getListCount(any())).thenReturn(100);
		
		String result = control.Searchboard(search, 1, model);
		assertEquals("board/freeboardList", result);
		verify(model).addAttribute("freeBoardList", mockList);
		verify(model).addAttribute("search", search);
		verify(model).addAttribute("paging", handler);
		
	}

	@Test
	public void testSearchRouteboard() {
		handler = new PagingHandler(1,100,5);
		search = new SearchCondition("title", "예시", 1);
		
		List<RouteBoard> mockList = new ArrayList<>();
		when(boardS.getRouteList(search, 1)).thenReturn(mockList);
		when(boardS.getRouteListCount(search)).thenReturn(100);
		
		String result = control.SearchRouteboard(search, 1, model);
		assertEquals("board/route", result);
		verify(model).addAttribute("routeBoardList", mockList);
		verify(model).addAttribute("search", search);
		verify(model).addAttribute("paging", handler);
	}

	@Test
	public void testRegBoard() {
		user = new User();
		when(session.getAttribute("user")).thenReturn(user);
		String result = control.regBoard(new Board(), session, model);
		assertEquals("board/freeboardForm", result);
		
		when(session.getAttribute("user")).thenReturn(null);
		String result1 = control.regBoard(new Board(), session, model);
		assertEquals("redirect:/user/login.do", result1);
	}

	@Test
	public void testRegBoard_sub() {
		board = new Board();		
		String result = control.regBoard_sub(board);
		verify(boardS).regBoard(board); 
		assertEquals("redirect:/board/free?type=-1", result);	
	}

	@Test
	public void testBoard() {
        String code = "testCode";
        Board mockBoard = new Board(); 

        List<Comment> mockComments = new ArrayList<>(); 
        Comment comment = new Comment();
        mockComments.add(comment);

        when(boardS.getBoardBycode(code)).thenReturn(mockBoard);
        when(commentS.getCommentByCode(code)).thenReturn(mockComments);
        when(commentS.count(code)).thenReturn(mockComments.size()); 
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/board/free/reg")); 

        String result = control.Board(code, model, session, request);

        verify(session).setAttribute("url", "http://localhost:8080/board/free/reg");
        verify(boardS).updateViews(code); 
        verify(model).addAttribute("freeboard", mockBoard); 
        verify(model).addAttribute("comments", mockComments); 
        verify(model).addAttribute("count", mockComments.size()); 
        assertEquals("board/freeboardDetail", result); 
	}

	@Test
	public void testComment() {
		User user = new User();
		Comment comment = new Comment();
		
		when(session.getAttribute("user")).thenReturn(user);
		when(session.getAttribute("url")).thenReturn("http://localhost:8080/board/free/");
	    ResponseEntity<Map<String, Object>> responseEntity = control.comment("b0001", comment, session);
        Map<String, Object> responseBody = responseEntity.getBody();
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseBody.get("status")); 
        assertEquals("Comment posted successfully", responseBody.get("message")); 
        assertEquals("/project/board/free/b0001", responseBody.get("redirect")); 
        
        when(session.getAttribute("user")).thenReturn(null);
        when(session.getAttribute("url")).thenReturn("http://localhost:8080/board/free/");
        
        responseEntity = control.comment("b0001", comment, session);
        responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // 상태 코드 확인
        assertEquals("error", responseBody.get("status")); // 상태 메시지 확인
        assertEquals("User not logged in", responseBody.get("message")); // 실패 메시지 확인
        assertEquals("/project/user/login.do", responseBody.get("redirect")); // 리다이렉션 URL 확인
	}

	@Test
	public void testCommentModi() {
		comment = new Comment();
		control.CommentModi("b0001", comment, session);
		verify(commentS).modify(comment);
	}

	@Test
	public void testCommentDel() {
		control.CommentDel("b0001", "c0001", session);
		verify(commentS).delete("c0001");
	}

	@Test
	public void testCheckLike() {
		Like like = new Like();
		when(likeS.checkLike(like)).thenReturn(like);
		int result = control.checkLike("b0001", like, session, model);
		assertEquals(1, result);
		verify(likeS).checkLike(like);
		
		when(likeS.checkLike(like)).thenReturn(null);
		int result2 = control.checkLike("b0001", like, session, model);
		assertEquals(0,result2);
	}

	@Test
	public void testIncrLike() {
		Like like = new Like();
		when(session.getAttribute("url")).thenReturn("free");
		Map<String, Object> result = control.incrLike("b0001", like, session);
		assertEquals("/project/board/free/b0001", result.get("redirect"));
		verify(likeS).incrLike(like);
		
		when(session.getAttribute("url")).thenReturn("route");
		Map<String, Object> result2 = control.incrLike("b0001", like, session);
		assertEquals("/project/board/route/b0001", result2.get("redirect"));
	}

	@Test
	public void testDecrLike() {
		Like like = new Like();
		when(session.getAttribute("url")).thenReturn("free");
		Map<String, Object> result = control.decrLike("b0001", like, session);
		assertEquals("/project/board/free/b0001", result.get("redirect"));
		verify(likeS).decrLike(like);
		
		when(session.getAttribute("url")).thenReturn("route");
		Map<String, Object> result2 = control.decrLike("b0001", like, session);
		assertEquals("/project/board/route/b0001", result2.get("redirect"));
	}

	@Test
	public void testCheckArch() {
		Archive arch = new Archive();
		when(archS.checkArch(arch)).thenReturn(arch);
		int result = control.checkArch("b0001", arch, session, model);
		verify(archS).checkArch(arch);
		assertEquals(1, result);
		
		when(archS.checkArch(arch)).thenReturn(null);
		int result2 = control.checkArch("b0001", arch, session, model);
		assertEquals(0, result2);
	}

	@Test
	public void testRegArch() {
		Archive arch = new Archive();
		when(session.getAttribute("url")).thenReturn("free");
		Map<String, Object> result = control.regArch("b0001", arch, session);
		assertEquals("/project/board/free/b0001", result.get("redirect"));
		verify(archS).insert(arch);
		
		when(session.getAttribute("url")).thenReturn("route");
		Map<String, Object> result2 = control.regArch("b0001", arch, session);
		assertEquals("/project/board/route/b0001", result2.get("redirect"));
	}

	@Test
	public void testDeleteArch() {
		Archive arch = new Archive();
		when(session.getAttribute("url")).thenReturn("free");
		Map<String, Object> result = control.deleteArch("b0001", arch, session);
		assertEquals("/project/board/free/b0001", result.get("redirect"));
		
		when(session.getAttribute("url")).thenReturn("route");
		Map<String,Object> result2 = control.deleteArch("b0001", arch, session);
		assertEquals("/project/board/route/b0001", result2.get("redirect"));
		verify(archS, times(2)).delete(arch);
	}

	@Test
	public void testRegReport() {
		user = new User(); Report report = new Report();
		when(session.getAttribute("user")).thenReturn(user);
		Map<String, Object> result = control.regReport("b0001",report , session);
		assertEquals("/project/board/free/b0001", result.get("redirect"));
		
		when(session.getAttribute("user")).thenReturn(null);
		Map<String, Object> result2 = control.regReport("b0001", report, session);
		assertEquals("/project/user/login.do", result2.get("redirect"));
	}

	@Test
	public void testBoardListTheme() {
		List<Board> mockList = new ArrayList<>();
		when(boardS.getBoardBytheme("1")).thenReturn(mockList);
		String result = control.BoardListTheme("1", model);
		assertEquals("board/freeboardList", result);
	}

	@Test
	public void testMyBoardScrap() {
	}

	@Test
	public void testMyfreeBoard() {
		board = new Board();
		when(boardS.getBoardBycode("b0001")).thenReturn(board);
		String result = control.myfreeBoard("b0001", session);
		assertEquals("/board/myboardForm",result);
		verify(session).setAttribute("myboard", board);
		verify(boardS).getBoardBycode("b0001");
	}

	@Test
	public void testBoardModi() {
		board = new Board("b0001", null, null, null, null, null, null, 0, 0, null, 0, 0, 0, 0);
		when(boardS.modiBoard(board)).thenReturn(10);
		String result = control.boardModi(board);
		assertEquals("redirect:/board/free/{code}", result);
		verify(boardS).modiBoard(board);
	}

	@Test
	public void testBoardDelete() {
		when(boardS.delBoard("b0001")).thenReturn(10);
		String result = control.boardDelete("b0001");
		assertEquals("redirect:/board/free?type=-1", result);
		verify(boardS).delBoard("b0001");
	}

	@Test
	public void testRouteboardDelete() {
		when(boardS.delBoard("b0001")).thenReturn(10);
		String result = control.routeboardDelete("b0001");
		assertEquals("redirect:/board/route", result);
		verify(boardS).delBoard("b0001");
	}

	@Test
	public void testFaq() {
		String result = control.faq();
		assertEquals("/board/faq", result);
	}

	@Test
	public void testInquiry() {
		user = new User();
		when(session.getAttribute("user")).thenReturn(user);
		String result = control.inquiry(session, model);
		assertEquals("board/inquiryForm", result);
		
		when(session.getAttribute("user")).thenReturn(null);
		String result2 = control.inquiry(session, model);
		assertEquals("redirect:/user/login.do", result2);
	}

	@Test
	public void testInquiryReg() throws IllegalStateException, IOException {
	   String fileDir = "test/uploads/";
        String originalName = "testImage.jpg";
        MockMultipartFile mockFile = new MockMultipartFile("boardImg", originalName, "image/jpeg", "test content".getBytes());

        BoardVO vo = new BoardVO();
        vo.setBoardImg(mockFile); vo.setBoardCode("b0001"); vo.setUserCode("u0001"); vo.setNickname("testUser");
        vo.setBoardTitle("Test Title"); vo.setBoardContent("Test Content"); vo.setBoardTheme(1); vo.setBoardTourdays(5); 
        vo.setBoardWritedate("2023-10-15"); vo.setBoardViews(0); vo.setBoardPoint(0); vo.setBoardType(1);

        String resultWithFile = control.inquiryReg(vo,fileDir); 

        assertEquals("redirect:/board/faq", resultWithFile);
        verify(boardS).regBoard(any(Board.class)); 

        MockMultipartFile emptyFile = new MockMultipartFile("boardImg", "", "", new byte[0]); 
        BoardVO voOut = new BoardVO(); 
        voOut.setBoardImg(emptyFile); voOut.setBoardCode("b0001"); voOut.setUserCode("u0001"); voOut.setNickname("testUser");
        voOut.setBoardTitle("Test Title"); voOut.setBoardContent("Test Content"); voOut.setBoardTheme(1); voOut.setBoardTourdays(5);
        voOut.setBoardWritedate("2023-10-15"); voOut.setBoardViews(0); voOut.setBoardPoint(0); voOut.setBoardType(1);

        String resultWithoutFile = control.inquiryReg(vo, fileDir); 

        assertEquals("redirect:/board/faq", resultWithoutFile); 
        verify(boardS, times(2)).regBoard(any(Board.class));
	}

	@Test
	public void testMyInquiryList() {
		user = new User();
		List<Board> mockList = new ArrayList<>();
		when(session.getAttribute("user")).thenReturn(user);
		when(boardS.getInquByuser(user.getUserId())).thenReturn(mockList);
		
		String result = control.myInquiryList(1, session, model);
		verify(model).addAttribute("BoardList", mockList);
		verify(boardS).getInquByuser(user.getUserId());
		assertEquals("/board/inquiryboard", result);
	}

	@Test
	public void testMyInquiry() {
		board = new Board();
		comment = new Comment();
		when(boardS.getBoardBycode("b0001")).thenReturn(board);
		when(session.getAttribute("user_id")).thenReturn("1");
		when(commentS.selectBoardIn("b0001")).thenReturn(comment);
		
		String result = control.myInquiry("b0001", session, model);
		verify(model).addAttribute("comment", comment);
		verify(model).addAttribute("myboard", board);
		verify(model).addAttribute("userId", "1");
		assertEquals("/board/answer", result);
	}

	@Test
	public void testInquiryAnswer() {
		board = new Board(); comment = new Comment();
		when(boardS.getBoardBycode("b0001")).thenReturn(board);
		when(commentS.selectBoardIn("b0001")).thenReturn(comment);
		when(session.getAttribute("user_id")).thenReturn("1");
		
		String result = control.inquiryAnswer("b0001", session, model);
		verify(model).addAttribute("comment", comment);
		verify(model).addAttribute("myboard", board);
		verify(model).addAttribute("userId", "1");
		assertEquals("/board/answerForm",result);
	}

	@Test
	public void testAnwser() {
		board = new Board();
		when(session.getAttribute("user_id")).thenReturn("1");
		when(boardS.getBoardBycode("b0001")).thenReturn(board);
		String result = control.anwser("b0001", comment, session, model);
		assertEquals("redirect:/user/mypage.do/inquiry", result);
		verify(model).addAttribute("myboard", board);
		verify(model).addAttribute("userId", "1");
		verify(commentS).register(comment);
	}

//	@Test
//	public void testImageDownload() {
//      
//	}

   @Test
    public void testRouteBoardList_WithSearchParameters() {
        // Arrange
        String region = "TestRegion";
        String theme = "TestTheme";
        String tourdays = "3";
        int page = 1;

        List<RouteBoard> mockRouteBoardList = new ArrayList<>();
        mockRouteBoardList.add(new RouteBoard()); // Mock 데이터 추가

        when(boardS.getRouteBoardBySearch(region, theme, tourdays, page)).thenReturn(mockRouteBoardList); // 모킹
        when(boardS.selectTotalCount(0)).thenReturn(10); // 총 레코드 수 모킹
        when(boardS.getTotalCountBySearch(region, theme, tourdays)).thenReturn(5); // 검색된 총 레코드 수 모킹

        // Act
        String viewName = control.routeBoardList(region, theme, tourdays, model, page); // 메서드 호출

        // Assert
        assertEquals("/board/route", viewName); // 반환된 뷰 이름 확인
        verify(model).addAttribute("routeBoardList", mockRouteBoardList); // 모델에 routeBoardList 추가 확인
        verify(model).addAttribute("type", 0); // 모델에 type 추가 확인
    }

    @Test
    public void testRouteBoardList_WithoutSearchParameters() {
        // Arrange
        String region = "";
        String theme = "";
        String tourdays = "";
        int page = 1;

        List<RouteBoard> mockRouteBoardList = new ArrayList<>();
        mockRouteBoardList.add(new RouteBoard()); // Mock 데이터 추가

        when(boardS.getRouteBoardBySearch(null, null, null, page)).thenReturn(mockRouteBoardList); // 모킹
        when(boardS.selectTotalCount(0)).thenReturn(10); // 총 레코드 수 모킹
        when(boardS.getTotalCountBySearch(null, null, null)).thenReturn(5); // 검색된 총 레코드 수 모킹

        // Act
        String viewName = control.routeBoardList(region, theme, tourdays, model, page); // 메서드 호출

        // Assert
        assertEquals("/board/route", viewName); // 반환된 뷰 이름 확인
        verify(model).addAttribute("routeBoardList", mockRouteBoardList); // 모델에 routeBoardList 추가 확인
        verify(model).addAttribute("type", 0); // 모델에 type 추가 확인
    }

	@Test
	public void testCreateRoute() {
		user = new User(); RouteBoard rboard = new RouteBoard();
		when(session.getAttribute("user")).thenReturn(user);
		String result = control.createRoute(rboard, session, model);
		assertEquals("board/createRouteForm", result);
		
		when(session.getAttribute("user")).thenReturn(null);
		String result2 = control.createRoute(rboard, session, model);
		assertEquals("redirect:/user/login.do", result2);
	}

	@Test
    public void testCreateRouteProcess_WithFile() throws Exception {
        // Arrange
        RouteBoardVO vo = new RouteBoardVO();
        vo.setBoardCode("board123");
        vo.setUserCode("user123");
        vo.setNickname("testUser");
        vo.setBoardTitle("Test Title");
        vo.setBoardContent("Test Content");
        vo.setBoardTheme(1);
        vo.setBoardTourdays(5);
        vo.setBoardWritedate("2023-10-15");
        vo.setBoardViews(0);
        vo.setBoardPoint(0);
        vo.setBoardType(1);
        vo.setBoardRegion(1);

        List<Day> dayPlans = new ArrayList<>();
        dayPlans.add(new Day(1, null, null)); // 하루 계획 예시 추가
        when(session.getAttribute("dayPlans")).thenReturn(dayPlans); // 세션에 dayPlans 설정

        MockMultipartFile file = new MockMultipartFile("boardImg", "testImage.jpg", "image/jpeg", "test content".getBytes());
        vo.setBoardImg(file); // MultipartFile 설정

        // Act
        String result = control.createRoute_process(vo, session);

        // Assert
        assertEquals("redirect:/board/route", result); // 리다이렉트 결과 확인
        verify(boardS).insertRoute(any(RouteBoard.class)); // insertRoute 메서드 호출 확인

        // Clean up: 삭제된 파일 확인
        File savedFile = new File("test/uploads/" + UUID.randomUUID() + ".jpg"); // 실제 파일 저장 경로 확인
    }

    @Test
    public void testCreateRouteProcess_WithoutFile() throws Exception {
        // Arrange
        RouteBoardVO vo = new RouteBoardVO();
        vo.setBoardCode("board123");
        vo.setUserCode("user123");
        vo.setNickname("testUser");
        vo.setBoardTitle("Test Title");
        vo.setBoardContent("Test Content");
        vo.setBoardTheme(1);
        vo.setBoardTourdays(5);
        vo.setBoardWritedate("2023-10-15");
        vo.setBoardViews(0);
        vo.setBoardPoint(0);
        vo.setBoardType(1);
        vo.setBoardRegion(1);

        List<Day> dayPlans = new ArrayList<>();
        dayPlans.add(new Day(1, null, null)); // 하루 계획 예시 추가
        when(session.getAttribute("dayPlans")).thenReturn(dayPlans); // 세션에 dayPlans 설정

        // Act
        String result = control.createRoute_process(vo, session);

        // Assert
        assertEquals("redirect:/board/route", result); // 리다이렉트 결과 확인
        verify(boardS).insertRoute(any(RouteBoard.class)); // insertRoute 메서드 호출 확인
    }
	
	@Test
    public void testHandleDayPlans_AddNewDayPlan() {
        // Arrange
        Day newDay = new Day();
        newDay.setDay(1); // 예시로 설정
        when(session.getAttribute("dayPlans")).thenReturn(null); // 세션에 dayPlans가 없을 때

        // Act
        ResponseEntity<String> response = control.handleDayPlans(newDay, session);

        // Assert
        assertEquals("Successfully added or updated a day plan.", response.getBody());
        verify(session).setAttribute(eq("dayPlans"), anyList()); // dayPlans가 세션에 설정되었는지 확인
    }
	
	@Test
    public void testHandleDayPlans_UpdateExistingDayPlan() {
        // Arrange
        Day existingDay = new Day();
        existingDay.setDay(1); // 예시로 설정
        List<Day> dayPlans = new ArrayList<>();
        dayPlans.add(existingDay);
        when(session.getAttribute("dayPlans")).thenReturn(dayPlans); // 세션에 기존 dayPlans가 있을 때

        Day updatedDay = new Day();
        updatedDay.setDay(1); // 동일한 day로 업데이트

        // Act
        ResponseEntity<String> response = control.handleDayPlans(updatedDay, session);

        // Assert
        assertEquals("Successfully added or updated a day plan.", response.getBody());
        assertEquals(1, dayPlans.size()); // 크기가 동일해야 함
        assertEquals(updatedDay, dayPlans.get(0)); // 업데이트된 내용 확인
    }

	@Test
    public void testShowRouteBoard_UserLoggedIn() throws Exception {
        // Arrange
        String boardCode = "board123";
        User mockUser = new User();
        mockUser.setUserCode("user123");
        when(session.getAttribute("user")).thenReturn(mockUser); // 로그인 상태 설정
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/route/board123")); // 요청 URL 설정
        RouteBoard mockRouteBoard = new RouteBoard();
        mockRouteBoard.setUserCode("user123"); // 작성자 코드 설정
        when(boardS.selectRoute(boardCode)).thenReturn(mockRouteBoard); // 모킹
        when(boardS.likeCount(boardCode)).thenReturn(5); // 모킹
        when(boardS.archCount(boardCode)).thenReturn(3); // 모킹
        when(commentS.getCommentByCode(boardCode)).thenReturn(new ArrayList<>()); // 모킹
        when(commentS.count(boardCode)).thenReturn(0); // 모킹

        // Act
        String viewName = control.showRouteBoard(boardCode, model, request, session);

        // Assert
        assertEquals("board/routePost", viewName); // 반환된 뷰 이름 확인
        verify(model).addAttribute("routeBoard", mockRouteBoard); // 모델에 routeBoard 추가 확인
        verify(model).addAttribute("like", 5); // 좋아요 수 추가 확인
        verify(model).addAttribute("arch", 3); // 아카이브 수 추가 확인
        verify(model).addAttribute("message", "O"); // 메시지 확인
        verify(model).addAttribute("comments", new ArrayList<>()); // 댓글 추가 확인
        verify(model).addAttribute("count", 0); // 댓글 수 추가 확인
    }

    @Test
    public void testShowRouteBoard_UserNotLoggedIn() throws Exception {
        // Arrange
        String boardCode = "board123";
        when(session.getAttribute("user")).thenReturn(null); // 로그인하지 않은 상태 설정
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/route/board123")); // 요청 URL 설정
        RouteBoard mockRouteBoard = new RouteBoard();
        when(boardS.selectRoute(boardCode)).thenReturn(mockRouteBoard); // 모킹
        when(boardS.likeCount(boardCode)).thenReturn(5); // 모킹
        when(boardS.archCount(boardCode)).thenReturn(3); // 모킹
        when(commentS.getCommentByCode(boardCode)).thenReturn(new ArrayList<>()); // 모킹
        when(commentS.count(boardCode)).thenReturn(0); // 모킹

        // Act
        String viewName = control.showRouteBoard(boardCode, model, request, session);

        // Assert
        assertEquals("board/routePost", viewName); // 반환된 뷰 이름 확인
        verify(model).addAttribute("routeBoard", mockRouteBoard); // 모델에 routeBoard 추가 확인
        verify(model).addAttribute("like", 5); // 좋아요 수 추가 확인
        verify(model).addAttribute("arch", 3); // 아카이브 수 추가 확인
        verify(model).addAttribute("comments", new ArrayList<>()); // 댓글 추가 확인
        verify(model).addAttribute("count", 0); // 댓글 수 추가 확인
    }

	@Test
	public void testRouteComment() {
		user = new User();
		when(session.getAttribute("user")).thenReturn(user);
		when(commentS.register(comment)).thenReturn(10);
		ResponseEntity<Map<String, Object>> result = control.routeComment("b0001", comment, session);
		assertEquals("/project/board/route/b0001", result.getBody().get("redirect"));
		verify(commentS).register(comment);
		
		when(session.getAttribute("user")).thenReturn(null);
		ResponseEntity<Map<String, Object>> result2 = control.routeComment("b0001", comment, session);
		assertEquals("/project/user/login.do", result2.getBody().get("redirect"));
	}

}
