package com.acorn.project.board.repository;

import java.util.List;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;

public interface BoardDAOI {

	// 게시글 수
	int selectTotalCount(int boardType);
	
	// 목록 전체조회
	List<Board> selectAll( int boardType,  int currentPage);

	// 1개 상세조회
	Board selectOne(String board_code);
	
	// 상세조회 시 view증가
	int updateViews(String boardCode);
	
	// theme로 조회
	List<Board> selectTheme(String board_theme);
	
	// user_id 게시물 수
	int selectUserCount(String user_id);
	
	// user_id로 조회
	List<Board> selectUser(String user_id, int currentPage);
	
	// 스크랩 게시물 수
	int MyArchCount(String user_id);
	
	//userId로 스크랩조회
	List<Board> selectUserArch(String user_id, int cuurentPage);
	
	// 좋아요 게시물 수
	int MyLikeCount(String user_id);
	
	//userId로 스크랩조회
	List<Board> selectUserLike(String user_id, int cuurentPage);
	
	// 댓글 게시물 수
	int MyComCount(String user_id);
	
	//userId로 댓글 조회
	List<Board> selectUserCom(String user_id, int cuurentPage);
	
	// 구매한 글 게시물 수
	int MyPointCount(String user_id);
	
	//userId로 구매한 글 조회
	List<Board> selectUserPoint(String user_id, int cuurentPage);
	
	// user_id+문의 조회
	List<Board> selectMyinqu(String user_id);
	
	// 게시판 등록
	int insertBoard(Board board);

	// 변경
	int updateBoard(Board board);

	// 삭제
	int deleteBoard(String board_code);

	// 제목, 작성자, 내용으로 검색
	List<Board> getList(SearchCondition search, int cuurentPage);
	
	// 검색 내용 수
	int getListCount(SearchCondition search);

	RouteBoard selectRoute(String boardCode);

	int insertRoute(RouteBoard routeBoard) throws Exception;

	List<String> selectMyBuyBoard(String userCode);

	List<RouteBoard> selectRouteAll(int boardType, int currentPage);

	List<RouteBoard> getRouteList(SearchCondition search, int currentPage);

	int getRouteListCount(SearchCondition search);	
	
	int likeCount(String boardCode);
	
	int archCount(String boardCode);

	List<RouteBoard> getRouteBoardBySearch(String region, String theme, String tourdays, int currentPage);

	int getTotalCountBySearch(String region, String theme, String tourdays);

	
	List<RouteBoard> homeRouteData(int boardRegion);
	
	// 게시글 수
	int selectInquiryCount();
		
	// 목록 전체조회
	List<Board> selectInquiry(int currentPage);

}