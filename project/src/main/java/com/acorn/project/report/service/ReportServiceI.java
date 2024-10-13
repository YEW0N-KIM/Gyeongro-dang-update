package com.acorn.project.report.service;

import java.util.List;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;

public interface ReportServiceI {

	int register(Report report);

	List<Report> getList();

	List<ReportCheck> selectReport(int currentPage);
	
	int reportCount();
}