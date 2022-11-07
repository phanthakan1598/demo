package com.example.demo.security.model;

import java.io.Serializable;

import com.example.demo.member.model.Role;
import com.example.demo.university.model.University;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserAuth implements Serializable{
	
	private static final long serialVersionUID = 8142376270878688021L;
	
	private long memberId; 
	private String memberUuid;
	private String memberUsername;
	private String memberPassword;
	private String memberNameTh;
	private String memberNameEn;
	private int memberStatus;
	private String memberImg;
//	private int enabled;
	
	private University university;
//	private String universityImg;
	private Role role;

}
