package com.acorn.project.point.service;

import java.util.List;
import java.util.Map;

import com.acorn.project.point.domain.Point;
import com.acorn.project.user.domain.User;

public interface PointServiceI {

	int count();

	List<Point> getListAll() throws Exception;

	List<Map<String, Object>> getPointOne(String userCode) throws Exception;

	int buyBoard(String userCode, String boardCode, int pointAmount) throws Exception;

	int buyPoint(int pointAmount, String userCode) throws Exception;

	int pointExchange(int pointAmount, String userCode) throws Exception;

	List<Map<String, Object>> getPointsWithinDateRange(String userCode, String startDate, String endDate) throws Exception;

	int sellBoard(String userCode, String boardCode, int pointAmount) throws Exception;
}