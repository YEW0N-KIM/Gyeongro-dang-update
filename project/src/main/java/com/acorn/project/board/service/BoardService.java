package com.acorn.project.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;
import com.acorn.project.board.repository.BoardDAO;

@Repository
public class BoardService implements BoardServiceI {
	
	@Autowired
	BoardDAO dao;
	
	@Override
	public int selectTotalCount(int boardType) {
		return dao.selectTotalCount(boardType);
	}
	
	@Override
	public List<Board> getBoardBytype(int boardType, int currentPage){		
		return dao.selectAll(  boardType, currentPage  );
	}
	
	@Override
	public List<RouteBoard> getRouteBoard(int boardType, int currentPage){		
		return dao.selectRouteAll(  boardType, currentPage  );
	}

	@Override
	public Board getBoardBycode(String board_code) {
		return dao.selectOne(board_code);
	}
	
	@Override
	public int updateViews(String boardCode) {
		return dao.updateViews(boardCode);
	}
	
	@Override
	public List<Board> getBoardBytheme(String board_theme) {
		return dao.selectTheme(board_theme);
	}
	
	@Override
	public int selectUserCount(String user_id) {
		return dao.selectUserCount(user_id);
	}
	
	@Override
	public List<Board> getBoardByuser(String user_id, int currentPage) {
		return dao.selectUser(user_id, currentPage);
	}
	
	@Override
	public int MyArchCount(String user_id) {
		return dao.MyArchCount(user_id);
	}
	
	@Override
	public List<Board> selectUserArch(String user_id, int currentPage){
		return dao.selectUserArch(user_id, currentPage);
	}
	
	@Override
	public int MyLikeCount(String user_id) {
		return dao.MyLikeCount(user_id);
	}
	
	@Override
	public List<Board> selectUserLike(String user_id, int currentPage){
		return dao.selectUserLike(user_id, currentPage);
	}
	
	@Override
	public int MyComCount(String user_id) {
		return dao.MyComCount(user_id);
	}
	
	@Override
	public List<Board> selectUserCom(String user_id, int currentPage){
		return dao.selectUserCom(user_id, currentPage);
	}
	
	@Override
	public int MyPointCount(String user_id) {
		return dao.MyPointCount(user_id);
	}
	
	@Override
	public List<Board> selectUserPoint(String user_id, int currentPage){
		return dao.selectUserPoint(user_id, currentPage);
	}
	
	@Override
	public List<Board> getInquByuser(String user_id) {
		return dao.selectMyinqu(user_id);
	}

	
	@Override
	public int regBoard(Board board) {
		return dao.insertBoard(board);
	}
	
	@Override
	public int modiBoard(Board board) {
		return dao.updateBoard(board);
	}
	
	@Override
	public int delBoard(String board_code) {
		return dao.deleteBoard(board_code);
	}

	@Override
	public List<Board> getList(SearchCondition search, int currentPage) {
		return dao.getList(search, currentPage);
	}
	
	@Override
	public List<RouteBoard> getRouteList(SearchCondition search, int currentPage) {
		return dao.getRouteList(search, currentPage);
	}
	
	@Override
	public int getListCount(SearchCondition search) {
		return dao.getListCount(search);
	}
	
	@Override
	public int getRouteListCount(SearchCondition search) {
		return dao.getRouteListCount(search);
	}


	@Override
	public RouteBoard selectRoute(String boardCode) throws Exception {
        return dao.selectRoute(boardCode);
    }
    
	@Override
	public int insertRoute(RouteBoard routeBoard) throws Exception {
        return dao.insertRoute(routeBoard);
    }
	
	@Override
	public List<String> selectMyBuyBoard(String userCode) {
		return dao.selectMyBuyBoard(userCode);
	}
	
	@Override
	public int likeCount (String boardCode) {
		return dao.likeCount(boardCode);
	}
	
	@Override
	public int archCount (String boardCode) {
		return dao.archCount(boardCode);
	}
	
	@Override
	public List<RouteBoard> getRouteBoardBySearch(String region, String theme, String tourdays, int currentPage) {
	    return dao.getRouteBoardBySearch(region, theme, tourdays, currentPage);
	}
	
	@Override
	public int getTotalCountBySearch(String region, String theme, String tourdays) {
		return dao.getTotalCountBySearch(region, theme, tourdays);
	}

	@Override
	public List<RouteBoard> getHomeRouteData(int boardRegion) {
		return dao.homeRouteData(boardRegion);
	}
	
	@Override
	// 게시판 수
	public int selectInquiryCount() {
		return dao.selectInquiryCount();
	}
	
	// 전체 목록 조회
	@Override
	public List<Board> selectInquiry(int currentPage){
		
		 //현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  
	 	
		
	 	
		Map info = new  HashMap();
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return dao.selectInquiry(currentPage);
	}
}