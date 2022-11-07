package com.example.demo.faculty.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.repository.ProgramRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class ProgramService {
	@Autowired
	ProgramRepository programRepository;
	
	public Program findProgramByUuid(String uuid) throws ProgramException {
        return programRepository.findOneByProgramUuid(uuid).orElseThrow(ProgramException::programNotFound);
    }
	
	public Program saveProgram(Program program) throws ProgramException {
    	if(Objects.isNull(program)) {
    		throw ProgramException.programIsEmptyOrNull();
    	}
		return programRepository.save(program);
	}
	
	public void delProgram(Program program) throws ProgramException {
    	if(Objects.isNull(program)) {
    		throw ProgramException.programIsEmptyOrNull();
    	}
    	programRepository.delete(program);
	}
	
	public List<Program> getAllProgram(Faculty faculty,DegreeLevel degreeLevel){
		return programRepository.findByFacultyAndDegreeLevel(faculty,degreeLevel);
	}
	
	public boolean getProgramNameTh(Faculty faculty,DegreeLevel degreeLevel, String nameTh){
        return programRepository.existsByFacultyAndDegreeLevelAndProgramNameTh(faculty, degreeLevel, nameTh);
    }
	
	public boolean getProgramNameEn(Faculty faculty,DegreeLevel degreeLevel, String nameEn){
        return programRepository.existsByFacultyAndDegreeLevelAndProgramNameEn(faculty, degreeLevel, nameEn);
    }
	
	public boolean getProgramCodeName(Faculty faculty,DegreeLevel degreeLevel, String codeName){
        return programRepository.existsByFacultyAndDegreeLevelAndProgramCodeName(faculty, degreeLevel, codeName);
    }
	

}
