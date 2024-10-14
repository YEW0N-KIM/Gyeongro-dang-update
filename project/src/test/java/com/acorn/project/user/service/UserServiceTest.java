package com.acorn.project.user.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.user.domain.User;
import com.acorn.project.user.repository.UserDAOI;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class UserServiceTest {

	@Autowired
	UserDAOI dao;
	
	@Autowired
	UserServiceI service;
	
	private HttpSession session;
	private User user = new User(null, "example","example","예시",null,null,"000-0000-0000",0);
	private User user2 = new User("u0005", "bb","bb123456","bb","n0004",null,"010-2345-6789",0);
	private String userId = "1";
	private String userCode = "u0001";
	
	 @Before
	    public void setUp() {
	        // MockHttpSession을 사용하여 세션 객체 생성
	        session = new MockHttpSession();
	        
	        // User 객체 초기화
	        user2 = new User("u0005", "bb", "bb123456", "bb", "n0004", null, "010-2345-6789", 0);
	    }
	

		
	@Test
	public void testGetUserById() throws Exception{
		User check = dao.selectById(userId);
		User result = service.getUserById(userId);
		assertEquals(check, result);
	}

	@Test
	public void testGetUserByCode() throws Exception{
		User check = dao.selectByCode(userCode);
		User result = service.getUserByCode(userCode);
		assertEquals(check,result);
	}

	@Test
	public void testGetUserList() throws Exception{
		List<User> check = dao.selectAll();
		List<User> result = service.getUserList();
		assertEquals(check,result);
	}

	@Test
	public void testRegUser() throws Exception{
		int result = service.regUser(user);
		assertEquals(1,result);
	}

	@Test
	public void testModUser() throws Exception{
		User modiUser = dao.selectById("example");
		modiUser.setNickname("example");
		int result = service.modUser(modiUser);
		assertEquals(1, result);
	}

	@Test
	public void testDelUser() throws Exception{
		int result = service.delUser("example");
		assertEquals(1, result);
	}

	@Test
	public void testLoginCheck() throws Exception{
		User check = dao.loginCheck(user2);
		User result = service.loginCheck(user2, session);
		assertEquals(check,result);
	}

//	@Test
//	public void testLogout() {
//		
//	}

	@Test
	public void testNicknameCheck() {
		int check = dao.nicknameCheck("n0001");
		int result = service.nicknameCheck("n0005");
		assertEquals(check, result);
	}

	@Test
	public void testUserTelCheck() {
		int check = dao.userTelCheck("010-1234-5678");
		int result = service.userTelCheck("010-1234-5678");
		assertEquals(check,result);
	}

	@Test
	public void testFindUserId() {
		String check = dao.findUserId("John Doe", "010-1234-5678");
		String result = service.findUserId("John Doe", "010-1234-5678");
		assertEquals(check, result);
	}

	@Test
	public void testFindUserPw() {
		String check = dao.findUserPw("example_id", "John Doe", "010-1234-5678");
		String result = service.findUserPw("example_id", "John Doe", "010-1234-5678");
		assertEquals(check,result);
	}

	@Test
	public void testUpdateUserPw() {
		int check = dao.updateUserPw("example_id", "example_pw");
		int result = service.updateUserPw("example_id", "example_pw");
		assertEquals(check, result);
	}

	@Test
	public void testUpdatePoint() {
		int result = service.updatePoint(10,user2);
		assertEquals(1, result);
	}

}
