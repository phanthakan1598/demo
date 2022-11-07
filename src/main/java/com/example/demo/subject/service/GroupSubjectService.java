package com.example.demo.subject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.subject.exception.GroupSubjectException;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.TypeSubject;
import com.example.demo.subject.repository.GroupSubjectRepository;
import com.example.demo.university.model.DegreeLevel;

import lombok.Setter;

@Service
@Setter
public class GroupSubjectService {
	@Autowired
	GroupSubjectRepository groupSubjectRepository;
	
	public GroupSubject findByGroupSubjectId(long id) throws GroupSubjectException {
        return groupSubjectRepository.findOneByGroupSubjectId(id).orElseThrow(GroupSubjectException::groupSubjectNotFound);
    }
	
	public GroupSubject findGroupSubjectByUuid(String uuid) throws GroupSubjectException {
        return groupSubjectRepository.findOneByGroupSubjectUuid(uuid).orElseThrow(GroupSubjectException::groupSubjectNotFound);
    }
	
	public GroupSubject saveGroupSubject(GroupSubject groupSubject) throws GroupSubjectException {
    	if(Objects.isNull(groupSubject)) {
    		throw GroupSubjectException.groupSubjectIsEmptyOrNull();
    	}
		return groupSubjectRepository.save(groupSubject);
	}
	
	public void delGroupSubject(GroupSubject groupSubject) throws  GroupSubjectException {
    	if(Objects.isNull(groupSubject)) {
    		throw GroupSubjectException.groupSubjectIsEmptyOrNull();
    	}
    	groupSubjectRepository.delete(groupSubject);
	}
	

	public Boolean booleanName(String name,TypeSubject typeSubject,DegreeLevel degreeLevel, Faculty faculty) {
		return groupSubjectRepository.existsByNameAndTypeSubjectAndDegreeLevelAndFaculty(name, typeSubject, degreeLevel, faculty);
	}
	
	public List<GroupSubject> groupSubjects (String typeSubject,DegreeLevel degreeLevel, Faculty faculty){
		return groupSubjectRepository.findByTypeSubjectTypeSubjectNameEnAndDegreeLevelAndFaculty(typeSubject, degreeLevel, faculty);
	}
}