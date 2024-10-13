package com.acorn.project.report.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCheck {
	String reportCode;
	String boardCode;
	String reportingUserCode;
	String reportedUserCode;
	String reportDate;
	String boardTitle;
	String reportingUserId;
	String reportedUserId;
}

