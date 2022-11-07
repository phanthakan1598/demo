package com.example.demo.faculty.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.repository.FacultyRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class FacultyService {
	@Autowired
	FacultyRepository facultyRepository;
	
	public boolean getFacultyNameTh(University university, String nameTh){
        return facultyRepository.existsByUniversityAndFacultyNameTh(university, nameTh);
    }
	
	public boolean getFacultyNameEn(University university, String nameEn){
        return facultyRepository.existsByUniversityAndFacultyNameEn(university, nameEn);
    }
	
	public boolean getFacultyCodeName(University university, String codeName){
        return facultyRepository.existsByUniversityAndFacultyCodeName(university, codeName);
    }
	
	public boolean getFaculty(University university){
        return facultyRepository.existsByUniversity(university);
    }
	
	public Faculty findFacultyByUuid(String uuid) throws FacultyException {
        return facultyRepository.findOneByFacultyUuid(uuid).orElseThrow(FacultyException::facultyNotFound);
    }
	
	public Faculty findFacultyCodeName(University university, String facultyUuid) throws FacultyException {
        return facultyRepository.findOneByUniversityAndFacultyCodeName(university, facultyUuid).orElseThrow(FacultyException::facultyNotFound);
    }
	
	
	
	public Faculty findFirstFacultyNumberDesc(University university) throws FacultyException {
        return facultyRepository.findFirstByUniversityOrderByFacultyNumberDesc(university).orElseThrow(FacultyException::facultyNotFound);
    }
	
	public Faculty saveFaculty(Faculty faculty) throws FacultyException {
    	if(Objects.isNull(faculty)) {
    		throw FacultyException.facultyIsEmptyOrNull();
    	}
		return facultyRepository.save(faculty);
	}
	
	public void delFaculty(Faculty faculty) throws FacultyException {
    	if(Objects.isNull(faculty)) {
    		throw FacultyException.facultyIsEmptyOrNull();
    	}
    	facultyRepository.delete(faculty);
	}
	
	public List<Faculty> getAllFaculty(University university){
		return facultyRepository.findByUniversityAndFacultyNumberNot(university,"00");
	}
	
	public List<Faculty> getAllFacultyAndGe(University university){
		return facultyRepository.findByUniversity(university);
	}
	

}
