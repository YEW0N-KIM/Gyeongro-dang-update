package com.acorn.project.point.controller;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
	
	private User user;
	
	@Test
	public void testShowMyPoint() {
		
	}

	@Test
	public void testShowMyEarnedPoint() {
	}

	@Test
	public void testShowMyUsePoint() {
	}

	@Test
	public void testChargeProcess() {
		user = new User();
	}

	@Test
	public void testExchangeProcess() throws Exception {
		
	}

	@Test
	public void testBuyBoardProcess() {
	}

}
