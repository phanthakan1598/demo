package com.example.demo.security.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDetailJson {
	
	String memberUuid;
	String memberUsername;
	String memberNameTh;
	String memberNameEn;
	String memberImg;
	int memberStatus;
	
	String universityUuid;
	String universityNameTh;
	String universityNameEn;
	String universityImg;
	
	String roleName;
	
	String facName;
	String proName;
	String degreeName;
	String degreeNameUid;
	
	String sId;
	
	String accessToken;
	String refreshToken;
	
	String dir;

}
