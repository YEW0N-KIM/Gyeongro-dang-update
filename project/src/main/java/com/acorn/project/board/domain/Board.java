package com.acorn.project.board.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private String boardCode;
	private String userCode;
	private String nickname;
	private String boardImgOrigin;
	private String boardImgReal;
	private String boardTitle;
	private String boardContent;
	private int boardTheme;
	private int boardTourdays;
	private String boardWritedate;
	private int boardViews;
	private int boardPoint;
	private int boardType;
	private int inquiry_check;
}