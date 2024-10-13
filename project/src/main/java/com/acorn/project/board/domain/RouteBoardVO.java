package com.acorn.project.board.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteBoardVO {
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
	private int boardRegion;
	private List<Day> days;
}