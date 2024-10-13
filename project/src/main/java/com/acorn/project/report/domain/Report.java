package com.acorn.project.report.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	String reportCode;
	String boardCode;
	String reportingUserCode;
	String reportedUserCode;
	String reportDate;
	String reportContent;
}

//reportCode varchar(255) primary key,
//boardCode VARCHAR(255) NOT NULL,
//reportingUserCode VARCHAR(255) NOT NULL,
//reportedUserCode VARCHAR(255) NOT NULL,
//FOREIGN KEY (reportedUserCode)
//REFERENCES usertbl(userCode) ON UPDATE CASCADE,
//reportDate datetime,
//reportContent varchar(255)
