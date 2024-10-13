
package com.acorn.project.point.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.acorn.project.point.domain.Point;
import com.acorn.project.point.repository.PointDAO;
import com.acorn.project.user.domain.User;

@Component
public class PointService implements PointServiceI {

    @Inject
    PointDAO dao;
    
    @Override
    public int count() {
        return dao.count();
    }
    
    @Override
    public List<Point> getListAll() throws Exception {
        return dao.selectAll();
    }
    
    @Override
    public List<Map<String, Object>> getPointOne(String userCode) throws Exception {
        return dao.selectOne(userCode);
    }
    
    @Override
    public int buyBoard(String userCode, String boardCode, int pointAmount) throws Exception {
        return dao.buyBoard(userCode, boardCode, pointAmount);
    }
    
	@Override
    public int sellBoard(String userCode, String boardCode, int pointAmount) throws Exception {
        return dao.sellBoard(userCode, boardCode, pointAmount);
    }

    @Override
    public int buyPoint(int pointAmount, String userCode) throws Exception {
        return dao.buyPoint(pointAmount, userCode);
    }

    @Override
    public int pointExchange(int pointAmount, String userCode) throws Exception {
        return dao.pointExchange(pointAmount, userCode);
    }
    
    @Override
    public List<Map<String, Object>> getPointsWithinDateRange(String userCode, String startDate, String endDate) throws Exception {
        return dao.selectPointsWithinDateRange(userCode, startDate, endDate);
    }
}
