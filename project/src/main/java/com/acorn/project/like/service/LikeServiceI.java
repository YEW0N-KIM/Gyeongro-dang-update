package com.acorn.project.like.service;

import com.acorn.project.like.domain.Like;

public interface LikeServiceI {

	int incrLike(Like like);

	int decrLike(Like like);

	Like checkLike(Like like);
}