package com.acorn.project.report.repository;

import java.util.List;

import com.acorn.project.report.domain.Report;
import com.acorn.project.report.domain.ReportCheck;

public interface ReportDAOI {

	List<Report> selectAll();

	int insert(Report report);

	
	int reportCount();

	List<ReportCheck> selectReport(int currentPage);
}