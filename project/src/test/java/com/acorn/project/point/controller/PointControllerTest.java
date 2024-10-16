package com.acorn.project.point.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.project.point.service.PointServiceI;
import com.acorn.project.user.domain.User;
import com.acorn.project.user.service.UserServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PointControllerTest {
	
	@Mock
	PointServiceI pointS;
	
	@Mock
	UserServiceI userS;
	
	@Mock
	HttpSession session;
	
	@InjectMocks
	PointController control;
	
	@Mock
	private User user = new User();
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(session.getAttribute("user")).thenReturn(user);
    }
		
//	@Test
//	public void testShowMyPoint() {
//		
//	}
//
//	@Test
//	public void testShowMyEarnedPoint() {
//	}
//
//	@Test
//	public void testShowMyUsePoint() {
//	}
//
//	@Test
//	public void testChargeProcess() {
//		user = new User();
//	}
//
//	@Test
//	public void testExchangeProcess() throws Exception {
//		
//	}
//
//	@Test
//	public void testBuyBoardProcess() {
//	}
	
    @Test
    public void testShowMyPoint_UserLoggedIn() throws Exception {
    	String userCode = "u0001";
        when(session.getAttribute("user")).thenReturn(user);
        when(user.getUserCode()).thenReturn(userCode);
        
        List<Map<String, Object>> pointList = new ArrayList<>();
        Map<String, Object> pointData = new HashMap<>();
        pointData.put("pointDate", "2024-05-31");
        pointData.put("pointStatus", 1);
        pointData.put("pointAmount", 100);
        pointList.add(pointData);
        
        when(pointS.getPointOne(userCode)).thenReturn(pointList); 

        ModelAndView mv = control.showMyPoint(session, null, null);

        assertEquals("point/showMyPoint", mv.getViewName());
        assertNotNull(mv.getModel().get("pointList"));
        assertEquals(pointList, mv.getModel().get("pointList"));
    }

    @Test
    public void testShowMyPoint_UserNotLoggedIn() throws Exception {
        when(session.getAttribute("user")).thenReturn(null);

        ModelAndView mv = control.showMyPoint(session, null, null);

        assertEquals("user/login", mv.getViewName());
        assertEquals("Login", mv.getModel().get("message"));
    }

    @Test
    public void testChargeProcess_UserLoggedIn() throws Exception {
        int pointAmount = 100;
        when(user.getUserCode()).thenReturn("testUser");
        when(pointS.buyPoint(pointAmount, "testUser")).thenReturn(1);
        when(userS.getUserById(anyString())).thenReturn(user);
        when(user.getUserPoint()).thenReturn(200); 

        int result = control.chargeProcess(pointAmount, session);

        assertEquals(200, result);
        verify(userS).updatePoint(pointAmount, user);
    }

    @Test(expected = IllegalStateException.class)
    public void testChargeProcess_UserNotLoggedIn() throws Exception {
        when(session.getAttribute("user")).thenReturn(null); 

        control.chargeProcess(100, session);
    }

    @Test
    public void testExchangeProcess_UserLoggedIn() throws Exception {
        int exchangeAmount = 50;
        when(user.getUserPoint()).thenReturn(100);
        when(user.getUserCode()).thenReturn("testUser");
        when(pointS.pointExchange(exchangeAmount, "testUser")).thenReturn(1);
        when(userS.getUserById(anyString())).thenReturn(user);

        int result = control.exchangeProcess(exchangeAmount, session);

        assertEquals(100, result);
        verify(userS).updatePoint(-exchangeAmount, user);
    }

//    @Test
//    public void testExchangeProcess_UserNotLoggedIn() throws Exception {
//        when(session.getAttribute("user")).thenReturn(null); 
//        int result = control.exchangeProcess(50, session);
//        
//        assertEquals(0, result);
//    }

    @Test
    public void testBuyBoardProcess_UserLoggedIn() throws Exception {
        int pointAmount = 100;
        String boardCode = "board1";
        String writerCode = "writer1";
        when(user.getUserCode()).thenReturn("testUser");
        when(user.getUserPoint()).thenReturn(200);
        when(pointS.buyBoard(anyString(), anyString(), anyInt())).thenReturn(1);
        when(userS.getUserByCode(writerCode)).thenReturn(new User());
        when(userS.getUserById(anyString())).thenReturn(user);

        int result = control.buyBoardProcess(pointAmount, boardCode, writerCode, session);

        assertEquals(1, result);
        verify(userS).updatePoint(-pointAmount, user);
    }

    @Test
    public void testBuyBoardProcess_UserNotLoggedIn() throws Exception {
        when(session.getAttribute("user")).thenReturn(null); 

        int result = control.buyBoardProcess(100, "board1", "writer1", session);

        assertEquals(0, result);
    }

}
