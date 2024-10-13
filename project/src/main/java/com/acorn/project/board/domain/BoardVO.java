package com.acorn.project.board.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private String boardCode;
	private String userCode;
	private String nickname;
	private  MultipartFile boardImg;
	private String boardTitle;
	private String boardContent;
	private int boardTheme;
	private int boardTourdays;
	private String boardWritedate;
	private int boardViews;
	private int boardPoint;
	private int boardType;
}