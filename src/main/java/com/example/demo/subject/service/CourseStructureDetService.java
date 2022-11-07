package com.example.demo.subject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.model.CourseStructureDet;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.repository.CourseStructureDetRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class CourseStructureDetService {
	@Autowired
	CourseStructureDetRepository courseStructureDetRepository;
	
	public CourseStructureDet findCourseStructureDetByUuid(String uuid) throws CourseStructureDetException {
        return courseStructureDetRepository.findOneByCourseStructureDetUuid(uuid).orElseThrow(CourseStructureDetException::courseStructureDetNotFound);
    }
	
	public CourseStructureDet saveCourseStructureDet(CourseStructureDet courseStructureDet) throws CourseStructureDetException {
    	if(Objects.isNull(courseStructureDet)) {
    		throw CourseStructureDetException.courseStructureDetIsEmptyOrNull();
    	}
		return courseStructureDetRepository.save(courseStructureDet);
	}
	
	public void delCourseStructureDet(CourseStructureDet courseStructureDet) throws  CourseStructureDetException {
    	if(Objects.isNull(courseStructureDet)) {
    		throw CourseStructureDetException.courseStructureDetIsEmptyOrNull();
    	}
    	courseStructureDetRepository.delete(courseStructureDet);
	}
	

	public Boolean booleanSubject(Subject subject,CourseStructure courseStructure) {
		return courseStructureDetRepository.existsBySubjectAndCourseStructure(subject, courseStructure);
	}
//	
//	public Boolean booleanName(Faculty faculty , String name) {
//		return courseStructureDetRepository.existsByProgramFacultyAndName(faculty, name);
//	}
//	
	public List<CourseStructureDet> courseStructureDets (CourseStructure courseStructure){
		return courseStructureDetRepository.findByCourseStructure(courseStructure);
	}
	
	public List<CourseStructureDet> courseStructureSubjectGe (DegreeLevel degreeLevel,String typeSubject){
		return courseStructureDetRepository.findByCourseStructureProgramDegreeLevelAndSubjectGroupSubjectTypeSubjectTypeSubjectNameEn(degreeLevel, typeSubject);
	}
	
	public List<CourseStructureDet> courseStructureSubject (DegreeLevel degreeLevel,Faculty faculty,String typeSubject){
		return courseStructureDetRepository.findByCourseStructureProgramDegreeLevelAndCourseStructureProgramFacultyAndSubjectGroupSubjectTypeSubjectTypeSubjectNameEn(degreeLevel, faculty, typeSubject);
	}
	
//	public List<CourseStructureDet> courseStructureSubject (DegreeLevel degreeLevel,Faculty faculty){
//		return courseStructureDetRepository.findByCourseStructureProgramDegreeLevelAndCourseStructureProgramFaculty(degreeLevel, faculty);
//	}
}