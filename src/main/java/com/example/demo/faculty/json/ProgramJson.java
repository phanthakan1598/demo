package com.example.demo.faculty.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.faculty.model.Program;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProgramJson {
	String ProgramUuid;
	String ProgramNameTh;
	String ProgramNameEn;
	String ProgramCodeName;
	String DegreeLevelName;
	String FacNameTh;
	String FacNameEn;
	
	
	public static ProgramJson packProgramJson(Program program) {
		ProgramJson programJson = new ProgramJson();
		programJson.setProgramUuid(program.getProgramUuid());
		programJson.setProgramNameTh(program.getProgramNameTh());
		programJson.setProgramNameEn(program.getProgramNameEn());
		programJson.setProgramCodeName(program.getProgramCodeName());
		programJson.setDegreeLevelName(program.getDegreeLevel().getName());
		programJson.setFacNameTh(program.getFaculty().getFacultyNameTh());
		programJson.setFacNameEn(program.getFaculty().getFacultyNameEn());
		return programJson;
	}
	
	public static List<ProgramJson> packProgramJson(List<Program> programs){
		List<ProgramJson> programJsons = new ArrayList<>();
		for(Program program : programs) {
			programJsons.add(packProgramJson(program));
		}
		return programJsons;
	}
}
