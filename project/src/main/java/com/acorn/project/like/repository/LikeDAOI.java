package com.acorn.project.like.repository;

import com.acorn.project.like.domain.Like;

public interface LikeDAOI {

	int insertLike(Like like);

	int deleteLike(Like like);
	
	Like selectOne(Like like);

}