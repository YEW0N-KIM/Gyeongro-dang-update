package com.acorn.project.report.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;
import com.acorn.project.report.repository.ReportDAOI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/test.xml" , "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} )
public class ReportServiceTest {
	
	@Autowired
	ReportDAOI dao;
	
	@Autowired
	ReportServiceI service;
	
	private Report report = new Report(null, "b0001", "u0001", "u0001",null,null);
	
	@Test
	public void testRegister() {
		int result = service.register(report);
		assertEquals(1, result);
	}

	@Test
	public void testGetList() {
		List<Report> check = dao.selectAll();
		List<Report> result = service.getList();
		assertEquals(check, result);
		
	}

	@Test
	public void testSelectReport() {
		List<ReportCheck> check = dao.selectReport(1);
		List<ReportCheck> result = service.selectReport(1);
		assertEquals(check,result);
	}

	@Test
	public void testReportCount() {
		int check = dao.reportCount();
		int result = service.reportCount();
		assertEquals(check,result);
	}

}
