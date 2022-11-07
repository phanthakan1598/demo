package com.example.demo.subject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.repository.CourseStructureRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class CourseStructureService {
	@Autowired
	CourseStructureRepository courseStructureRepository;
	
	public CourseStructure findCourseStructureByUuid(String uuid) throws CourseStructureException {
        return courseStructureRepository.findOneByCourseStructureUuid(uuid).orElseThrow(CourseStructureException::courseStructureNotFound);
    }
	
	public CourseStructure saveCourseStructure(CourseStructure courseStructure) throws CourseStructureException {
    	if(Objects.isNull(courseStructure)) {
    		throw CourseStructureException.courseStructureIsEmptyOrNull();
    	}
		return courseStructureRepository.save(courseStructure);
	}
	
	public void delCourseStructure(CourseStructure courseStructure) throws  CourseStructureException {
    	if(Objects.isNull(courseStructure)) {
    		throw CourseStructureException.courseStructureIsEmptyOrNull();
    	}
    	courseStructureRepository.delete(courseStructure);
	}
	
	public Boolean booleanCourseStructure(DegreeLevel degreeLevel) {
		return courseStructureRepository.existsByProgramDegreeLevel(degreeLevel);
	}

	public Boolean booleanProgram(Program program) {
		return courseStructureRepository.existsByProgram(program);
	}
	
	public Boolean booleanName(Faculty faculty , String name) {
		return courseStructureRepository.existsByProgramFacultyAndName(faculty, name);
	}
	
	public List<CourseStructure> courseStructures (DegreeLevel degreeLevel, Faculty faculty){
		return courseStructureRepository.findByProgramDegreeLevelAndProgramFaculty( degreeLevel, faculty);
	}
	
	public List<CourseStructure> courseStructureDegreeLevels (DegreeLevel degreeLevel){
		return courseStructureRepository.findByProgramDegreeLevel(degreeLevel);
	}
}