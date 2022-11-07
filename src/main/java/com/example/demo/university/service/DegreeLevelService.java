package com.example.demo.university.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;
import com.example.demo.university.repository.DegreeLevelRepository;

import lombok.Setter;

@Service
@Setter
public class DegreeLevelService {
	@Autowired
	DegreeLevelRepository degreeLevelRepository;
	
	public DegreeLevel findDegreeLevelByUuid(String uuid) throws DegreeLevelException {
        return degreeLevelRepository.findOneByDegreeLevelUuid(uuid).orElseThrow(DegreeLevelException::degreeLevelNotFound);
    }
	
	public DegreeLevel saveDegreeLevel(DegreeLevel degreeLevel) throws DegreeLevelException {
    	if(Objects.isNull(degreeLevel)) {
    		throw DegreeLevelException.degreeLevelIsEmptyOrNull();
    	}
		return degreeLevelRepository.save(degreeLevel);
	}
	
	public void delDegreeLevel(DegreeLevel degreeLevel) throws DegreeLevelException {
    	if(Objects.isNull(degreeLevel)) {
    		throw DegreeLevelException.degreeLevelIsEmptyOrNull();
    	}
    	degreeLevelRepository.delete(degreeLevel);
	}
	
	public List<DegreeLevel> getAllDegreeLevel (University university){
		return degreeLevelRepository.findByUniversity(university);
	}
}
