package com.acorn.project.user.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userCode;
	private String userId;
	private String userPw;
	private String userName;
	private String nickname;
	private Date userBirth;
	private String userTel;
	private int userPoint;
}
