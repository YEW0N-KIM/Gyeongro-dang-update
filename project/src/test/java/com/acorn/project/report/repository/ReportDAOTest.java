package com.acorn.project.report.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;

@RunWith(MockitoJUnitRunner.class)
public class ReportDAOTest {

	@Mock
	SqlSession session;
	
	@InjectMocks
	ReportDAO dao;
	
	private String namespace = "com.acorn.reportMapper.";
	private Report report = new Report();
	
	@Test
	public void testSelectAll() {
		when(session.selectList(namespace+"selectAll")).thenReturn(Arrays.asList(report));
		List<Report> result = dao.selectAll();
		assertEquals(1, result.size());
	}

	@Test
	public void testInsert() {
		when(session.insert(namespace+"insert", report)).thenReturn(10);
		int result = dao.insert(report);
		assertEquals(10, result);
	}

	@Test
	public void testSelectReport() {
		int  pageSize  =   10;		
		int currentPage = 1;
	    int offset = (currentPage - 1) * pageSize;  
	 	
		Map info = new  HashMap();
		info.put("offset",  offset);   
		info.put("pageSize", pageSize);
		when(session.selectList(namespace+"selectReport", info)).thenReturn(Arrays.asList(report));
		List<ReportCheck> result = dao.selectReport(currentPage);
		assertEquals(1,result.size());
	}

	@Test
	public void testReportCount() {
		when(session.selectOne(namespace+"selectReportCount")).thenReturn(10);
		int result = dao.reportCount();
		assertEquals(10, result);
	}

}
