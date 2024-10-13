package com.acorn.project.user.repository;

import java.util.List;

import com.acorn.project.user.domain.User;

public interface UserDAOI {

	int count() throws Exception;
	

	User selectById(String userId) throws Exception;

	List<User> selectAll() throws Exception;

	int insert(User user) throws Exception;

	int update(User user) throws Exception;

	int delete(String userId) throws Exception;
	
	User loginCheck(User user);
	
	int nicknameCheck(String nickname);
	
	int userTelCheck(String userTel);
	
	String findUserId(String userName, String userTel);
	
	String findUserPw(String userId, String userName, String userTel);
	
	int updateUserPw(String userId, String newPw);
	
	public int updatePoint(int pointAmount, User user);


	User selectByCode(String userCode) throws Exception;
}