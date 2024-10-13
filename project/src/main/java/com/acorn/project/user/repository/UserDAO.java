package com.acorn.project.user.repository;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.user.domain.User;


@Repository
public class UserDAO implements UserDAOI  {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.acorn.UserMapper.";

    @Override
	public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }     
    
    @Override
	public User selectById(String userId) throws Exception {
        return session.selectOne(namespace + "selectById", userId);
    }
    
    @Override
    public User selectByCode(String userCode) throws Exception {
        return session.selectOne(namespace + "selectByCode", userCode);
    }
    
    @Override
	public List<User> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }
    
    @Override
	public int insert(User user) throws Exception {
        return session.insert(namespace+"insert", user);
    }
    
    @Override
	public int update(User user) throws Exception {
        return session.update(namespace+"update", user);
    }    

    @Override
	public int delete(String userId) throws Exception {          
        return   session.delete(namespace+"delete", userId);
    }
    
	@Override
    public User loginCheck(User user) {
        return session.selectOne(namespace + "login_check", user);
    }

	@Override
	public int nicknameCheck(String nickname) {
		return session.selectOne(namespace+"checkNickname", nickname);
	}
	
	@Override
	public int userTelCheck(String userTel) {
		return session.selectOne(namespace+"checkUserTel", userTel);
	}
	
	@Override
	public String findUserId(String userName, String userTel) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("userTel", userTel);
        
        return session.selectOne(namespace+"find_id", paramMap);
    }
	
	@Override
	public String findUserPw(String userId, String userName, String userTel) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("userName", userName);
        paramMap.put("userTel", userTel);
        
        return session.selectOne(namespace+"find_pw", paramMap);
    }

	@Override
	public int updateUserPw(String userId, String newPw) {
		Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("userPw", newPw);
        
        return session.update(namespace+"update_pw", paramMap);
	}
	
	public int updatePoint(int pointAmount, User user) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("pointAmount", user.getUserPoint() + pointAmount);
	    paramMap.put("userCode", user.getUserCode());
	        
	    return session.update(namespace + "update_point", paramMap);
	}
}

 