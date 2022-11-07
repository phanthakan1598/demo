package com.example.demo.university.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.university.model.DegreeLevel;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DegreeLevelAllJson {
	String degreeLevelUuid;
	String degreeLevelname;
	
	public static DegreeLevelAllJson packDegreeLevelPersonalAllJson(DegreeLevel degreeLevel) {
		DegreeLevelAllJson degreeLevelCourseAllJson = new DegreeLevelAllJson();
		degreeLevelCourseAllJson.setDegreeLevelUuid(degreeLevel.getDegreeLevelUuid());
		degreeLevelCourseAllJson.setDegreeLevelname(degreeLevel.getName());
		return degreeLevelCourseAllJson;
	}
	
	public static List<DegreeLevelAllJson> degreeLevelCourseAllJsons(List<DegreeLevel> degreeLevels){
		List<DegreeLevelAllJson> degreeLevelCourseAllJsons = new ArrayList<>();
		for(DegreeLevel degreeLevel : degreeLevels) {
			degreeLevelCourseAllJsons.add(packDegreeLevelPersonalAllJson(degreeLevel));
		}
		return degreeLevelCourseAllJsons;
	}
}
