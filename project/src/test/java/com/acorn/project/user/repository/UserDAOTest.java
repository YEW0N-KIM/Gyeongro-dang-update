package com.acorn.project.user.repository;

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

import com.acorn.project.user.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

	@Mock
	SqlSession session;
	
	@InjectMocks
	UserDAO dao;
	
	private String namespace = "com.acorn.UserMapper.";
	private String userId = "1";
	private User user = new User();
	private String nickname = "n0001";
	private String userCode = "u0001";
	private String userTel = "010-0000-0000";
	private String userName = "John";
	
	@Test
	public void testCount() throws Exception{
		when(session.selectOne(namespace+"count")).thenReturn(10);
		int result = dao.count();
		assertEquals(10, result);
	}

	@Test
	public void testSelectById() throws Exception{
		when(session.selectOne(namespace+"selectById", userId)).thenReturn(user);
		User result = dao.selectById(userId);
		assertEquals(user, result);
	}

	@Test
	public void testSelectByCode() throws Exception{
		when(session.selectOne(namespace+"selectByCode",userCode)).thenReturn(user);
		User result = dao.selectByCode(userCode);
		assertEquals(user, result);
	}

	@Test
	public void testSelectAll() throws Exception{
		when(session.selectList(namespace+"selectAll")).thenReturn(Arrays.asList(user));
		List<User> result = dao.selectAll();
		assertEquals(1, result.size());
	}

	@Test
	public void testInsert() throws Exception{
		when(session.insert(namespace+"insert", user)).thenReturn(10);
		int result = dao.insert(user);
		assertEquals(10, result);
	}

	@Test
	public void testUpdate() throws Exception{
		when(session.update(namespace+"update",user)).thenReturn(10);
		int result = dao.update(user);
		assertEquals(10, result);
	}

	@Test
	public void testDelete() throws Exception{
		when(session.delete(namespace+"delete", userId)).thenReturn(10);
		int result = dao.delete(userId);
		assertEquals(10, result);
	}

	@Test
	public void testLoginCheck() {
		when(session.selectOne(namespace+"login_check", user)).thenReturn(user);
		User result = dao.loginCheck(user);
		assertEquals(user, result);
	}

	@Test
	public void testNicknameCheck() {
		when(session.selectOne(namespace+"checkNickname", nickname)).thenReturn(10);
		int result = dao.nicknameCheck(nickname);
		assertEquals(10,result);
	}

	@Test
	public void testUserTelCheck() {
		when(session.selectOne(namespace+"checkUserTel",userTel)).thenReturn(10);
		int result = dao.userTelCheck(userTel);
		assertEquals(10, result);
	}

	@Test
	public void testFindUserId() {
		 Map<String, String> paramMap = new HashMap<>();
		 paramMap.put("userName", userName);
	        paramMap.put("userTel", userTel);
        when(session.selectOne(namespace+"find_id", paramMap)).thenReturn("0");
        String result = dao.findUserId(userName, userTel);
        assertEquals("0", result);
	}

	@Test
	public void testFindUserPw() {
		 Map<String, String> paramMap = new HashMap<>();
	        paramMap.put("userId", userId);
	        paramMap.put("userName", userName);
	        paramMap.put("userTel", userTel);
	        when(session.selectOne(namespace+"find_pw", paramMap)).thenReturn("0");
	        String result = dao.findUserPw(userId,userName, userTel);
	        assertEquals("0", result);
	}

	@Test
	public void testUpdateUserPw() {
		String newPw = "11";
		Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("userPw", newPw);
        when(session.update(namespace+"update_pw",paramMap)).thenReturn(10);
        int result = dao.updateUserPw(userId, newPw);
        assertEquals(10,result);
	}

	@Test
	public void testUpdatePoint() {
		int pointAmount = 0;
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("pointAmount", user.getUserPoint() + pointAmount);
	    paramMap.put("userCode", user.getUserCode());
	    when(session.update(namespace+"update_point", paramMap)).thenReturn(10);
	    int result = dao.updatePoint(pointAmount, user);
	    assertEquals(10, result);
	}

}
