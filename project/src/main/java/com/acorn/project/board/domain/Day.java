package com.acorn.project.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Day {
	private int day;
	private String dayInfo;
	private List<Marker> markers;
}