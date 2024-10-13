package com.acorn.project.report.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;

@Repository
public class ReportDAO implements ReportDAOI {
	
	@Autowired
	private SqlSession session;
	private String namespace="com.acorn.reportMapper.";
	
	
	@Override
	public List<Report> selectAll(){
		return session.selectList(namespace+"selectAll");
	}
	
	
	@Override
	public int insert(Report report) {
		return session.insert(namespace+"insert", report);
	}
	
	
	@Override
	public List<ReportCheck> selectReport(int currentPage){
		//현재페이지정보,  전체레코드수      
	  	int  pageSize  =   10;		
		 int offset = (currentPage - 1) * pageSize;  
	 	
		
	 	
		Map info = new  HashMap();
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		
		return session.selectList(namespace+"selectReport",info);
	}
	
	@Override
	public int reportCount() {
		return session.selectOne(namespace+"selectReportCount");
	}
	
}
