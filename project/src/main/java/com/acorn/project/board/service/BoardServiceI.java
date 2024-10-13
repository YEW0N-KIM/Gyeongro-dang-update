package com.acorn.project.board.service;

import java.util.List;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;

public interface BoardServiceI {
	
	int selectTotalCount(int boardType);
	
	List<Board> getBoardBytype(int board_type,int Page);

	Board getBoardBycode(String board_code);
	
	int updateViews(String boardCode);
	
	List<Board> getBoardBytheme(String board_theme);
	
	int selectUserCount(String user_id);
	
	List<Board> getBoardByuser(String user_id, int currentPage);
	
	int MyArchCount(String user_id);
	
	List<Board> selectUserArch(String user_id, int cuurentPage);
	
	int MyLikeCount(String user_id);
	
	List<Board> selectUserLike(String user_id, int cuurentPage);
	
	int MyComCount(String user_id);
	
	List<Board> selectUserCom(String user_id, int cuurentPage);
	
	int MyPointCount(String user_id);
	
	List<Board> selectUserPoint(String user_id, int cuurentPage);
	
	List<Board> getInquByuser(String user_id);

	int regBoard(Board board);

	int modiBoard(Board board);

	int delBoard(String board_code);
	
	List<Board> getList(SearchCondition search, int page);
	
	int getListCount(SearchCondition search);

	RouteBoard selectRoute(String boardCode) throws Exception;

	int insertRoute(RouteBoard routeBoard) throws Exception;

	List<String> selectMyBuyBoard(String userCode);

	List<RouteBoard> getRouteBoard(int boardType, int currentPage);

	List<RouteBoard> getRouteList(SearchCondition search, int currentPage);

	int getRouteListCount(SearchCondition search);
	
	int likeCount(String boardCode);

	int archCount(String boardCode);

	List<RouteBoard> getRouteBoardBySearch(String region, String theme, String tourdays, int currentPage);

	int getTotalCountBySearch(String region, String theme, String tourdays);

	
	List<RouteBoard> getHomeRouteData(int boardRegion);
	
	int selectInquiryCount();
			
	List<Board> selectInquiry(int currentPage);

}