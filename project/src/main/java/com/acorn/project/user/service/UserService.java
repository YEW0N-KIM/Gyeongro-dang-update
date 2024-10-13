package com.acorn.project.user.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.acorn.project.user.domain.User;
import com.acorn.project.user.repository.UserDAO;

@Component
public class UserService implements UserServiceI {
	@Inject
	UserDAO dao;

		@Override
		public User getUserById(String userId) throws Exception {
	        return dao.selectById(userId);
	    }
		
		@Override
		public User getUserByCode(String userCode) throws Exception {
	        return dao.selectByCode(userCode);
	    }
		
		@Override
		public List<User> getUserList() throws Exception {
	        return dao.selectAll();
	    }
	    
		@Override
		public int regUser(User user) throws Exception {
	        return dao.insert(user);
	    }
	    
		@Override
		public int modUser(User user) throws Exception {
	        return dao.update(user);
	    }    
	
		@Override
		public int delUser(String userId) throws Exception {          
	        return   dao.delete(userId);
		}
		
		@Override
		public User loginCheck(User user, HttpSession session) {
	    	user = dao.loginCheck(user);
	    	if(user != null) {
	    		session.setAttribute("user", user);
	    	}
	        return user;
	    }
		
		@Override
		public void logout(HttpSession session ) {
			session.invalidate();
		}
		
		@Override
		public int nicknameCheck(String nickname) {
			return dao.nicknameCheck(nickname);
		}
		
		@Override
		public int userTelCheck(String userTel) {
			return dao.userTelCheck(userTel);
		}

		@Override
		public String findUserId(String userName, String userTel) {
			return dao.findUserId(userName, userTel);
		}
		
		@Override
		public String findUserPw(String userId, String userName, String userTel) {
			return dao.findUserPw(userId, userName, userTel);
		}
		
		@Override
		public int updateUserPw(String userId, String newPw) {
			return dao.updateUserPw(userId, newPw);
		}

		@Override
		public int updatePoint(int pointAmount, User user) {
			return dao.updatePoint(pointAmount, user);
		}
}