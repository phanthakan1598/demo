package com.example.demo.subject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.subject.exception.TypeSubjectException;
import com.example.demo.subject.model.TypeSubject;
import com.example.demo.subject.repository.TypeSubjectRepository;

import lombok.Setter;

@Service
@Setter
public class TypeSubjectService {
	@Autowired
	TypeSubjectRepository typeSubjectRepository;
	
	public TypeSubject findTypeSubjectByUuid(String uuid) throws TypeSubjectException {
        return typeSubjectRepository.findOneByTypeSubjectUuid(uuid).orElseThrow(TypeSubjectException::typeSubjectNotFound);
    }
	
	public List<TypeSubject> typeSubjects (){
		return typeSubjectRepository.findAll();
	}
}
