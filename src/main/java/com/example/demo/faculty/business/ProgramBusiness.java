package com.example.demo.faculty.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.json.ProgramJson;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.payload.ProgramInsertPayload;
import com.example.demo.faculty.payload.ProgramUpdatePayload;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class ProgramBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	
	public void insertProgram(ProgramInsertPayload body)throws ProgramException, FacultyException, DegreeLevelException, LogException{
		String programNameTh = body.getProgramNameTh();
		String programNameEn = body.getProgramNameEn();
		String programCodeName = body.getProgramCodeName();
		Faculty faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
		
		if(programNameTh == null || programNameTh.equals("")) {
			throw ProgramException.programNameThEmpty();
		}
		if(programNameEn == null || programNameEn.equals("")) {
			throw ProgramException.programNameEnEmpty();
		}
		if(programCodeName == null || programCodeName.equals("")) {
			throw ProgramException.programCodeNameEmpty();
		}
		
		if(programService.getProgramNameTh(faculty, degreeLevel, programNameTh)) {
			throw ProgramException.programNameThUsed();
		}
		if(programService.getProgramNameEn(faculty, degreeLevel, programNameEn)) {
			throw ProgramException.programNameEnUsed();
		}
		if(programService.getProgramCodeName(faculty, degreeLevel, programCodeName)) {
			throw ProgramException.programCodeNameUsed();
		}
		
		Program program = new Program();
		program.setProgramNameTh(programNameTh);
		program.setProgramNameEn(programNameEn);
		program.setProgramCodeName(programCodeName);
		program.setFaculty(faculty);
		program.setDegreeLevel(degreeLevel);
		programService.saveProgram(program);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลสาขา");
		logService.saveLog(log);
	}
	
	public void updateProgram(ProgramUpdatePayload body) throws ProgramException, LogException {
		Program program = programService.findProgramByUuid(body.getProgramUuid());
		
		String programNameThOld = program.getProgramNameTh();
		String programNameEnOld =  program.getProgramNameEn();
		String programCodeNameOld = program.getProgramCodeName();
		
		Faculty faculty = program.getFaculty();
		DegreeLevel degreeLevel = program.getDegreeLevel();
		
		String programNameTh = body.getProgramNameTh();
		String programNameEn = body.getProgramNameEn();
		String programCodeName = body.getProgramCodeName();
		
		if(programNameTh == null || programNameTh.equals("")) {
			throw ProgramException.programNameThEmpty();
		}
		if(programNameEn == null || programNameEn.equals("")) {
			throw ProgramException.programNameEnEmpty();
		}
		if(programCodeName == null || programCodeName.equals("")) {
			throw ProgramException.programCodeNameEmpty();
		}
		
		if(!programNameThOld.equals(programNameTh)){
			if(programService.getProgramNameTh(faculty, degreeLevel, programNameTh)) {
				throw ProgramException.programNameThUsed();
			}
		}
		if(!programNameEnOld.equals(programNameEn)){
			if(programService.getProgramNameEn(faculty, degreeLevel, programNameEn)) {
				throw ProgramException.programNameEnUsed();
			}
		}
		if(!programCodeNameOld.equals(programCodeName)){
			if(programService.getProgramCodeName(faculty, degreeLevel, programCodeName)) {
				throw ProgramException.programCodeNameUsed();
			}
		}
		
		
		program.setProgramNameTh(programNameTh);
		program.setProgramNameEn(programNameEn);
		program.setProgramCodeName(programCodeName);
		programService.saveProgram(program);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลสาขา");
		logService.saveLog(log);
	}
	
	public void delProgram(String uid) throws ProgramException, LogException {
		Program program = programService.findProgramByUuid(uid);
		programService.delProgram(program);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลสาขา");
		logService.saveLog(log);
		
	}
	
	public List<ProgramJson> viewAllProgram(String facUid,String degreeUid) throws FacultyException, DegreeLevelException {
		return ProgramJson.packProgramJson(programService.getAllProgram(facultyService.findFacultyByUuid(facUid),degreeLevelService.findDegreeLevelByUuid(degreeUid)));
	}
	
	public ProgramJson viewProgram(String uid) throws ProgramException  {
		return ProgramJson.packProgramJson(programService.findProgramByUuid(uid));
	}
	
}
