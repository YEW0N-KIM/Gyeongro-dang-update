package com.acorn.project.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.like.domain.Like;
import com.acorn.project.like.repository.LikeDAOI;

@Repository
public class LikeService implements LikeServiceI {
	
	@Autowired
	LikeDAOI dao;
	
	@Override
	public int incrLike(Like like) {
		return dao.insertLike(like);
	}
	
	@Override
	public int decrLike(Like like) {
		return dao.deleteLike(like);
	}

	@Override
	public Like checkLike(Like like) {
		return dao.selectOne(like);
	}
	
	
}
