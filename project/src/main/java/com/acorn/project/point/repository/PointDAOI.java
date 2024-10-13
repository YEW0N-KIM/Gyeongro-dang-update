package com.acorn.project.point.repository;

import java.util.List;
import java.util.Map;

import com.acorn.project.point.domain.Point;
import com.acorn.project.user.domain.User;

public interface PointDAOI {

	int count();

	List<Point> selectAll() throws Exception;

	List<Map<String, Object>> selectOne(String userCode) throws Exception;
	
	int buyPoint(int pointAmount, String userCode) throws Exception;

	int buyBoard(String userCode, String boardCode, int pointAmount) throws Exception;

	int pointExchange(int pointAmount, String userCode) throws Exception;

	List<Map<String, Object>> selectPointsWithinDateRange(String userCode, String startDate, String endDate) throws Exception;

	int sellBoard(String userCode, String boardCode, int pointAmount) throws Exception;
	
}