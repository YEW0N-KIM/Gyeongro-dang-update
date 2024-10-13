package com.acorn.project.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;
import com.acorn.project.report.repository.ReportDAO;

@Repository
public class ReportService implements ReportServiceI {
	
	@Autowired
	ReportDAO dao;
	
	
	@Override
	public int register (Report report) {
		return dao.insert(report);
	}
	
	@Override
	public List<Report> getList(){
		return dao.selectAll();
	}
	
	@Override
	public List<ReportCheck> selectReport(int currentPage){
		return dao.selectReport(currentPage);
	}
	
	@Override
	public int reportCount() {
		return dao.reportCount();
	}
}
