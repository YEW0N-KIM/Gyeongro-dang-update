package com.acorn.project.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.board.domain.Board;
import com.acorn.project.board.domain.RouteBoard;
import com.acorn.project.board.domain.SearchCondition;

@Repository
public class BoardDAO implements BoardDAOI {
	
	@Autowired
	private SqlSession session;
	private String namespace = "com.acorn.boardMapper.";
	
	@Override
	// 게시판 수
	public int selectTotalCount(int boardType) {
		return session.selectOne(namespace+"selectTotalCount",boardType);
	}
	
	// 전체 목록 조회
	@Override
	public List<Board> selectAll( int boardType ,   int currentPage){
		
		 //현재페이지정보,  전체레코드수      
	  	int  pageSize  =   15;		
		 int offset = (currentPage - 1) * pageSize;  
	 	
		
	 	
		Map info = new  HashMap();
		info.put("boardType",  boardType);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectAll", info );
	}
	
	@Override
	public List<RouteBoard> selectRouteAll( int boardType ,   int currentPage){
		
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   5;		
		 int offset = (currentPage - 1) * pageSize;  
	 	
		
	 	
		Map info = new  HashMap();
		info.put("boardType",  boardType);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectRouteAll", info );
	}
	
	// 1개 상세조회
	@Override
	public Board selectOne(String board_code) {
		return session.selectOne(namespace+"selectOne", board_code);
	}
	
	// 상세조회 시 view증가
	@Override
	public int updateViews(String boardCode) {
		return session.update(namespace+"updateView", boardCode);
	}
	
	// theme 조회
	@Override
	public List<Board> selectTheme(String board_theme) {
		return session.selectList(namespace+"selectTheme",board_theme);
	}
	
	@Override
	public int selectUserCount(String user_id) {
		return session.selectOne(namespace+"selectMyCount", user_id);
	}
	
	// user_id로 조회
	@Override
	public List<Board> selectUser(String user_id, int currentPage) {
		
		 //현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new  HashMap();
		info.put("userId", user_id);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectMy",info);
	}
	
	@Override
	public int MyArchCount(String user_id) {
		return session.selectOne(namespace+"MyArchCount",user_id);
	}
	
	// userId로 스크랩조회
	@Override
	public List<Board> selectUserArch(String user_id, int currentPage){
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new  HashMap();
		info.put("userId", user_id);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectMyarchive",info);
	}
	
	// userId, 좋아요 수 
	@Override
	public int MyLikeCount(String user_id) {
		return session.selectOne(namespace+"MyLikeCount",user_id);
	}
	
	// userId로 좋아요 조회
	@Override
	public List<Board> selectUserLike(String user_id, int currentPage){
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new  HashMap();
		info.put("userId", user_id);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectMylike",info);
	}
	
	// userId, 댓글 수 
	@Override
	public int MyComCount(String user_id) {
		return session.selectOne(namespace+"MyComCount",user_id);
	}
	
	// userId로 댓글 조회
	@Override
	public List<Board> selectUserCom(String user_id, int currentPage){
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new  HashMap();
		info.put("userId", user_id);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectMycom",info);
	}
	
	// userId, 구매 게시글 수 
	@Override
	public int MyPointCount(String user_id) {
		return session.selectOne(namespace+"MyPointCount",user_id);
	}
	
	// userId로 구매 게시글 조회
	@Override
	public List<Board> selectUserPoint(String user_id, int currentPage){
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  

		Map<String, Object> info = new  HashMap();
		info.put("userId", user_id);
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectMypoint",info);
	}
	
	// user_id+문의 조회
	@Override
	public List<Board> selectMyinqu(String user_id) {
		return session.selectList(namespace+"selectMyinqu",user_id);
	}
	
	// 게시판 등록
	@Override
	public int insertBoard(Board board) {
		return session.insert(namespace+"insert",board);
	}
	
	// 변경
	@Override
	public int updateBoard(Board board) {
		return session.update(namespace+"update",board);
	}
	
	// 삭제
	@Override
	public int deleteBoard(String board_code) {
		return session.delete(namespace+"delete",board_code);
	}
	
	// 제목, 작성자, 내용으로 검색
	@Override
	public List<Board> getList(SearchCondition search, int currentPage){		
		
		int pageSize=15;	
		int  start  =   (currentPage  -1) *pageSize;
	 	search.setStart(start);

		return session.selectList(namespace+"selectSearch",search);
	}
	
	@Override
	public List<RouteBoard> getRouteList(SearchCondition search, int currentPage){		
		
		int pageSize=5;	
		int  start  =   (currentPage  -1) *pageSize;
	 	search.setStart(start);

		return session.selectList(namespace+"selectRouteSearch",search);
	}
	
	// 검색 내용 수
	@Override
	public int getListCount(SearchCondition search) {
		return session.selectOne(namespace+"selectSearchCount", search);
	}

	@Override
	public int getRouteListCount(SearchCondition search) {
		return session.selectOne(namespace+"selectSearchRouteCount", search);
	}
	
	@Override
	public RouteBoard selectRoute(String boardCode) { 
		return session.selectOne(namespace+"selectRoute", boardCode);
	}
	 
	
	@Override
	 public int insertRoute(RouteBoard routeBoard) {
	    return session.insert(namespace + "insertRoute", routeBoard);
	}
	
	@Override
	public List<String> selectMyBuyBoard(String userCode) {
		return session.selectList(namespace + "selectMyBuyBoard", userCode);
	}
	
	@Override
	public int likeCount(String boardCode) {
		return session.selectOne(namespace + "LikeCount", boardCode);
	}
	
	@Override
	public int archCount(String boardCode) {
		return session.selectOne(namespace + "ArchCount", boardCode);
	}
	
	@Override
	public List<RouteBoard> getRouteBoardBySearch(String region, String theme, String tourdays, int currentPage) {
	    int pageSize = 5;
	    int offset = (currentPage - 1) * pageSize;
	    
	    Map<String, Object> info = new HashMap<>();
	    info.put("region", region);
	    info.put("theme", theme);
	    info.put("tourdays", tourdays);
	    info.put("offset", offset);
	    info.put("pageSize", pageSize);
	    
	    return session.selectList(namespace + "getRouteBoardBySearch", info);
	}
	
	@Override
	public int getTotalCountBySearch(String region, String theme, String tourdays) {
	    Map<String, Object> info = new HashMap<>();
	    info.put("region", region);
	    info.put("theme", theme);
	    info.put("tourdays", tourdays);
	    
	    return session.selectOne(namespace + "getTotalCountBySearch", info);
	}

	@Override
    public List<RouteBoard> homeRouteData(int boardRegion) {
        return session.selectList(namespace + "homeRouteData", boardRegion);
    }
	
	@Override
	// 게시판 수
	public int selectInquiryCount() {
		return session.selectOne(namespace+"selectInquiryCount");
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
		
		return session.selectList(namespace+"selectInquiry", info );
	}
	

}