package com.example.demo.subject.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.subject.model.GroupSubject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupSubjectAllJson {
	String groupSubjectUuid;
	String groupSubjectName;
	
	public static GroupSubjectAllJson packGroupSubjectPersonalAllJson(GroupSubject groupSubject) {
		GroupSubjectAllJson groupSubjectCourseAllJson = new GroupSubjectAllJson();
		groupSubjectCourseAllJson.setGroupSubjectUuid(groupSubject.getGroupSubjectUuid());
		groupSubjectCourseAllJson.setGroupSubjectName(groupSubject.getName());
		return groupSubjectCourseAllJson;
	}
	
	public static List<GroupSubjectAllJson> groupSubjectCourseAllJsons(List<GroupSubject> groupSubjects){
		List<GroupSubjectAllJson> groupSubjectCourseAllJsons = new ArrayList<>();
		for(GroupSubject groupSubject : groupSubjects) {
			groupSubjectCourseAllJsons.add(packGroupSubjectPersonalAllJson(groupSubject));
		}
		return groupSubjectCourseAllJsons;
	}
}
