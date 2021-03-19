package com.sbs.example.lolHi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String email;
	
	public boolean isAdmin() {
		return "test1".equals(loginId);
	}
}
