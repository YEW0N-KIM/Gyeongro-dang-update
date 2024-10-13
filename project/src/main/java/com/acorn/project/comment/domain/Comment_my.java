package com.acorn.project.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment_my {
	String commentCode;
	String boardTitle;
	int boardType;
	String boardCode;
	String userCode;
	String commentContent;
	String commentDate;
}
