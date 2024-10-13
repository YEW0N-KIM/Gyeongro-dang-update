package com.acorn.project.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	String commentCode;
	String nickname;
	String boardCode;
	String userCode;
	String commentContent;
	String commentDate;
}
