package com.example.demo.course.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.repository.StudyProgramRepository;
import com.example.demo.faculty.model.Program;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class StudyProgramService {
	@Autowired
	StudyProgramRepository studyProgramRepository;
	
	public StudyProgram findStudyProgramByUuid(String uuid) throws StudyProgramException {
        return studyProgramRepository.findOneByStudyProgramUuid(uuid).orElseThrow(StudyProgramException::studyProgramNotFound);
    }
	
	public StudyProgram findProgram(Program program) throws StudyProgramException {
        return studyProgramRepository.findOneByCourseStructureProgram(program).orElseThrow(StudyProgramException::studyProgramNotFound);
    }
	
	public StudyProgram saveStudyProgram(StudyProgram studyProgram) throws StudyProgramException {
    	if(Objects.isNull(studyProgram)) {
    		throw StudyProgramException.studyProgramIsEmptyOrNull();
    	}
		return studyProgramRepository.save(studyProgram);
	}
	
	public void delStudyProgram(StudyProgram studyProgram) throws StudyProgramException {
    	if(Objects.isNull(studyProgram)) {
    		throw StudyProgramException.studyProgramIsEmptyOrNull();
    	}
    	studyProgramRepository.delete(studyProgram);
	}
	
	public Boolean booleanStudyProgram (CourseStructure courseStructure) {
		return studyProgramRepository.existsByCourseStructure(courseStructure);
	}
	
	public Boolean booleanStudyProgramName (String name, CourseStructure courseStructure) {
		return studyProgramRepository.existsByNameAndCourseStructure(name,courseStructure);
	}
	
	public List<StudyProgram> studyPrograms (DegreeLevel degreeLevel){
		return studyProgramRepository.findByCourseStructureProgramDegreeLevel(degreeLevel);
	}
}