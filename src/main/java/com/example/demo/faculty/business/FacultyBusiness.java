package com.example.demo.faculty.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.json.FacultyJson;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.payload.FacultyInsertPayload;
import com.example.demo.faculty.payload.FacultyUpdatePayload;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.model.University;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class FacultyBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public void insertFaculty(FacultyInsertPayload body)throws FacultyException, MemberException, LogException{
		String facultyCodeName = body.getFacultyCodeName();
		String facultyNameTh = body.getFacultyNameTh();
		String facultyNameEn = body.getFacultyNameEn();
		
		University university = jwtAuth.getCurrentUser().getUniversity();
		
		if(facultyNameTh == null || facultyNameTh.equals("")) {
			throw FacultyException.facultyNameThEmpty();
		}
		if(facultyNameEn == null || facultyNameEn.equals("")) {
			throw FacultyException.facultyNameEnEmpty();
		}
		if(facultyCodeName == null || facultyCodeName.equals("")) {
			throw FacultyException.facultyCodeNameEmpty();
		}
		
		if(facultyService.getFacultyNameTh(university, facultyNameTh)) {
			throw FacultyException.facultyNameThUsed();
		}
		if(facultyService.getFacultyNameEn(university, facultyNameEn)) {
			throw FacultyException.facultyNameEnUsed();
		}
		if(facultyService.getFacultyCodeName(university, facultyCodeName)) {
			throw FacultyException.facultyCodeNameUsed();
		}
		
		String facultyNumber = null;
		
		if(facultyService.getFaculty(university)) {
			int facultyMaxNum = Integer.parseInt(facultyService.findFirstFacultyNumberDesc(university).getFacultyNumber());
			facultyNumber = Integer.toString(facultyMaxNum + 1);
		}else {
			facultyNumber = "0";
		}
		
		if (Integer.parseInt(facultyNumber) > 99) {
			throw FacultyException.facultyOver();
		}
		
		if (facultyNumber.length() < 2) {
			facultyNumber = 0+facultyNumber;
		}
		
		
		Faculty faculty = new Faculty();
		faculty.setFacultyNameTh(facultyNameTh);
		faculty.setFacultyNameEn(facultyNameEn);
		faculty.setFacultyCodeName(facultyCodeName);
		faculty.setFacultyNumber(facultyNumber);
		faculty.setUniversity(jwtAuth.getCurrentUser().getUniversity());
		facultyService.saveFaculty(faculty);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลคณะ");
		logService.saveLog(log);
	}
	
	public void updateFaculty(FacultyUpdatePayload body) throws FacultyException, LogException {
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		
		String facultyCodeName = body.getFacultyCodeName();
		String facultyNameTh = body.getFacultyNameTh();
		String facultyNameEn = body.getFacultyNameEn();
		
		String facultyNameThOld = faculty.getFacultyNameTh();
		String facultyNameEnOld = faculty.getFacultyNameEn();
		String facultyCodeNameOld = faculty.getFacultyCodeName();
		
		University university = jwtAuth.getCurrentUser().getUniversity();
		
		if(facultyNameTh == null || facultyNameTh.equals("")) {
			throw FacultyException.facultyNameThEmpty();
		}
		if(facultyNameEn == null || facultyNameEn.equals("")) {
			throw FacultyException.facultyNameEnEmpty();
		}
		if(facultyCodeName == null || facultyCodeName.equals("")) {
			throw FacultyException.facultyCodeNameEmpty();
		}
		
		if(!facultyCodeNameOld.equals(facultyCodeName)){
			if(facultyService.getFacultyCodeName(university, facultyCodeName)) {
				throw FacultyException.facultyCodeNameUsed();
			}
		}
		if(!facultyNameThOld.equals(facultyNameTh)){
			if(facultyService.getFacultyNameTh(university, facultyNameTh)) {
				throw FacultyException.facultyNameThUsed();
			}
		}
		if(!facultyNameEnOld.equals(facultyNameEn)){
			if(facultyService.getFacultyNameEn(university, facultyNameEn)) {
				throw FacultyException.facultyNameEnUsed();
			}
		}
		
		
		faculty.setFacultyNameTh(facultyNameTh);
		faculty.setFacultyNameEn(facultyNameEn);
		faculty.setFacultyCodeName(facultyCodeName);
		facultyService.saveFaculty(faculty);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลคณะ");
		logService.saveLog(log);
	}
	
	public void delFaculty(String uid) throws FacultyException, LogException {
		Faculty faculty = facultyService.findFacultyByUuid(uid);
		facultyService.delFaculty(faculty);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลคณะ");
		logService.saveLog(log);
		
	}
	
	public List<FacultyJson> viewAllFaculty() {
		return FacultyJson.packFacultyJson(facultyService.getAllFaculty(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public List<FacultyJson> viewAllFacultyAndGe() {
		return FacultyJson.packFacultyJson(facultyService.getAllFacultyAndGe(jwtAuth.getCurrentUser().getUniversity()));
	}
	
	public FacultyJson viewFaculty(String uid) throws FacultyException  {
		return FacultyJson.packFacultyJson(facultyService.findFacultyByUuid(uid));
	}
	
}
