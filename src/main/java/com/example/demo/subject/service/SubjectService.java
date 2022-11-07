package com.example.demo.subject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.repository.SubjectRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class SubjectService {
	@Autowired
	SubjectRepository subjectRepository;
	
	public Subject findBySubjectId(long id) throws SubjectException {
        return subjectRepository.findOneBySubjectId(id).orElseThrow(SubjectException::subjectNotFound);
    }
	
	public Subject findSubjectByUuid(String uuid) throws SubjectException {
        return subjectRepository.findOneBySubjectUuid(uuid).orElseThrow(SubjectException::subjectNotFound);
    }
	public Subject findSubjectBySubjectCode(String subjectCode,DegreeLevel degreeLevel) throws SubjectException {
        return subjectRepository.findOneBySubjectCodeAndGroupSubjectDegreeLevel(subjectCode,degreeLevel).orElseThrow(SubjectException::subjectNotFound);
    }
	
	
	public Subject saveSubject(Subject subject) throws SubjectException {
    	if(Objects.isNull(subject)) {
    		throw SubjectException.subjectIsEmptyOrNull();
    	}
		return subjectRepository.save(subject);
	}
	
	public void delSubject(Subject subject) throws  SubjectException {
    	if(Objects.isNull(subject)) {
    		throw SubjectException.subjectIsEmptyOrNull();
    	}
    	subjectRepository.delete(subject);
	}
	
	public Boolean booleanSubjectDegreeLevel(String subjectCode,DegreeLevel degreeLevel) {
		return subjectRepository.existsBySubjectCodeAndGroupSubjectDegreeLevel(subjectCode, degreeLevel);
	}
	
	public Boolean booleanSubjectDegreeLevelUsed(String subjectCode,DegreeLevel degreeLevel,long subId) {
		return subjectRepository.existsBySubjectCodeAndGroupSubjectDegreeLevelAndSubjectIdNot(subjectCode, degreeLevel, subId);
	}
	
	public Boolean booleanName(String name,GroupSubject groupSubject) {
		return subjectRepository.existsByNameAndGroupSubject(name, groupSubject);
	}
	
	public Boolean booleanSubject(Faculty faculty) {
		return subjectRepository.existsByGroupSubjectFaculty(faculty);
	}
	
//	public Subject findFirstSubjectNumberDesc(Faculty faculty) throws SubjectException {
//        return subjectRepository.findFirstByGroupSubjectFacultyOrderBySubjectNumberDesc(faculty).orElseThrow(SubjectException::subjectNotFound);
//    }
	
	public List<Subject> subjects (GroupSubject groupSubject){
		return subjectRepository.findByGroupSubject(groupSubject);
	}
	
	public List<Subject> subjectDegreeLevels (DegreeLevel degreeLevel){
		return subjectRepository.findByGroupSubjectDegreeLevel(degreeLevel);
	}
	
	
	public List<Subject> subjectGes (DegreeLevel degreeLevel){
		return subjectRepository.findByGroupSubjectDegreeLevelAndGroupSubjectTypeSubjectTypeSubjectNameEn(degreeLevel, "GE");
	}
}