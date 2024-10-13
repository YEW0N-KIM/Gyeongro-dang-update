package com.acorn.project.event.domain;

import org.json.JSONObject;

import com.acorn.project.like.domain.Like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EventDTO3 {
	
	Object firstimage;
	Object overview;
	double mapx;
	double mapy;
	Object addr1;
	Object addr2;
	Object title;

}
