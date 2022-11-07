package com.example.demo.faculty.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.faculty.model.Faculty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FacultyJson {
	String facultyUuid;
	String facultyNameTh;
	String facultyNameEn;
	String facultyCodeName;
	String facultyNum;
	
	public static FacultyJson packFacultyJson(Faculty faculty) {
		FacultyJson facultyJson = new FacultyJson();
		facultyJson.setFacultyUuid(faculty.getFacultyUuid());
		facultyJson.setFacultyNameTh(faculty.getFacultyNameTh());
		facultyJson.setFacultyNameEn(faculty.getFacultyNameEn());
		facultyJson.setFacultyCodeName(faculty.getFacultyCodeName());
		facultyJson.setFacultyNum(faculty.getFacultyNumber());
		return facultyJson;
	}
	
	public static List<FacultyJson> packFacultyJson(List<Faculty> faculties){
		List<FacultyJson> facultyJsons = new ArrayList<>();
		for(Faculty faculty : faculties) {
			facultyJsons.add(packFacultyJson(faculty));
		}
		return facultyJsons;
	}
}
