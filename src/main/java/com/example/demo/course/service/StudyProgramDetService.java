package com.example.demo.course.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.exception.StudyProgramDetException;
import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.model.StudyProgramDet;
import com.example.demo.course.repository.StudyProgramDetRepository;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class StudyProgramDetService {
	@Autowired
	StudyProgramDetRepository studyProgramDetRepository;
	
	public StudyProgramDet findStudyProgramDetByUuid(String uuid) throws StudyProgramDetException {
        return studyProgramDetRepository.findOneByStudyProgramDetUuid(uuid).orElseThrow(StudyProgramDetException::studyProgramDetNotFound);
    }
	
	public StudyProgramDet findSubjectCode(String SubjectCode) throws StudyProgramDetException {
        return studyProgramDetRepository.findOneBySubjectCode(SubjectCode).orElseThrow(StudyProgramDetException::studyProgramDetNotFound);
    }
	
	public StudyProgramDet saveStudyProgramDet(StudyProgramDet studyProgramDet) throws StudyProgramDetException {
    	if(Objects.isNull(studyProgramDet)) {
    		throw StudyProgramDetException.studyProgramDetIsEmptyOrNull();
    	}
		return studyProgramDetRepository.save(studyProgramDet);
	}
	
	public void delStudyProgramDet(StudyProgramDet studyProgramDet) throws StudyProgramDetException {
    	if(Objects.isNull(studyProgramDet)) {
    		throw StudyProgramDetException.studyProgramDetIsEmptyOrNull();
    	}
    	studyProgramDetRepository.delete(studyProgramDet);
	}
	
	public Boolean booleanStudySubject(String subjectCode,StudyProgram studyProgram) {
		return studyProgramDetRepository.existsBySubjectCodeAndStudyProgram(subjectCode, studyProgram);
	}
	
//	public Boolean booleanStudyProgramDetCourseGroupDet(CourseGroupDet courseGroupDet) {
//		return studyProgramDetRepository.existsByCourseGroupDet(courseGroupDet);
//	}
	public Boolean booleanSubjectCode(String subjectCode,Faculty faculty,DegreeLevel degreeLevel) {
		return studyProgramDetRepository.existsBySubjectCodeAndStudyProgramCourseStructureProgramFacultyAndStudyProgramCourseStructureProgramDegreeLevel(subjectCode, faculty, degreeLevel);
	}
	
	public List<StudyProgramDet> studyProgramDets (StudyProgram studyProgram){
		return studyProgramDetRepository.findByStudyProgramOrderByLevelClassAscTermAsc(studyProgram);
	}
	
	public List<StudyProgramDet> studyFacSub (Faculty faculty, DegreeLevel degreeLevel,String type){
		return studyProgramDetRepository.findByStudyProgramCourseStructureProgramFacultyAndStudyProgramCourseStructureProgramDegreeLevelAndTypeSubjectTypeSubjectNameEn(faculty, degreeLevel, type);
	}
	
	public List<StudyProgramDet> studyProSub (Program program){
		return studyProgramDetRepository.findByStudyProgramCourseStructureProgramAndTypeSubjectTypeSubjectNameEn(program, "MAJOR");
	}
	
	
}
