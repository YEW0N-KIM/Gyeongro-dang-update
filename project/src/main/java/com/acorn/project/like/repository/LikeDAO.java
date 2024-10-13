package com.acorn.project.like.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.like.domain.Like;

@Repository
public class LikeDAO implements LikeDAOI {
	
	@Autowired
	private SqlSession session;
	String namespace =  "com.acorn.likeMapper.";

	@Override
	public int insertLike(Like like) {
		return session.insert(namespace+"incrLike", like);
	}
	
	@Override
	public int deleteLike(Like like) {
		return session.delete(namespace+"decrLike", like);
	}

	@Override
	public Like selectOne(Like like) {
		return session.selectOne(namespace+"selectOne", like);
	}
}
