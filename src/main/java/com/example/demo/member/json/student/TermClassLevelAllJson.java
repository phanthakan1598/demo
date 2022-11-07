package com.example.demo.member.json.student;

import com.example.demo.university.model.Term;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TermClassLevelAllJson {
	String term;
	String levelClass;
	String termUid;
	
	public static TermClassLevelAllJson packTermClassLevelAllJson(Term term ,String levelClass) {
		TermClassLevelAllJson termClassLevelAllJson = new TermClassLevelAllJson();
		termClassLevelAllJson.setTerm(Integer.toString(term.getTerm()));
		termClassLevelAllJson.setLevelClass(levelClass);
		termClassLevelAllJson.setTermUid(term.getTermUuid());
		return termClassLevelAllJson;
	}
	
//	public static List<TermClassLevelAllJson> termClassLevelAllJsons(List<MemberFaculty> memberFaculties){
//		List<TermClassLevelAllJson> termClassLevelAllJsons = new ArrayList<>();
//		for(MemberFaculty memberFaculty : memberFaculties) {
//			termClassLevelAllJsons.add(packTermClassLevelAllJson(memberFaculty));
//		}
//		return termClassLevelAllJsons;
//	}
}
