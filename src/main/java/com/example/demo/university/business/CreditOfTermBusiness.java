package com.example.demo.university.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.CreditOfTermException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.json.CreditOfTermAllJson;
import com.example.demo.university.model.CreditOfTerm;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.payload.creditOfTerm.CreditOfTermInsertPayload;
import com.example.demo.university.payload.creditOfTerm.CreditOfTermUpdatePayload;
import com.example.demo.university.service.CreditOfTermService;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class CreditOfTermBusiness {
	
	@Autowired
	CreditOfTermService creditOfTermService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	public List<CreditOfTermAllJson> viewAllCreditOfTerm(String facUid,String degreeUid) throws  FacultyException, DegreeLevelException {
		Faculty faculty = facultyService.findFacultyByUuid(facUid);
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeUid);
		return CreditOfTermAllJson.creditOfTermCourseAllJsons(creditOfTermService.listCreditOfTerms(faculty,degreeLevel));
	}
	
	public List<CreditOfTermAllJson> viewAllCreditOfTermProgram(String proUid) throws ProgramException {
		Program program = programService.findProgramByUuid(proUid);
		return CreditOfTermAllJson.creditOfTermCourseAllJsons(creditOfTermService.listCreditOfTermsProgram(program));
	}
	
	public CreditOfTermAllJson viewCreditOfTerm(String uid) throws CreditOfTermException  {
		CreditOfTerm creditOfTerm = creditOfTermService.findCreditOfTermByUuid(uid);
		return CreditOfTermAllJson.packCreditOfTermPersonalAllJson(creditOfTerm);
	}
	
	public void insertCreditOfTerm(CreditOfTermInsertPayload body) throws ProgramException, CreditOfTermException, LogException  {
		String creditMin = body.getCreditMin();
		String creditMax = body.getCreditMax();
		Program program = programService.findProgramByUuid(body.getProgramUid());
		
		if(creditMin == null || creditMin.equals("")) {
			throw CreditOfTermException.creditOfTermCreditMinEmpty();
		}
		
		if(creditMax == null || creditMax.equals("")) {
			throw CreditOfTermException.creditOfTermCreditMaxEmpty();
		}
		if(Integer.parseInt(creditMin) > Integer.parseInt(creditMax)) {
			throw CreditOfTermException.creditIncorrect();
		}
		if(creditOfTermService.booleanCreditOfTermProgram(program)) {
			throw CreditOfTermException.creditOfTermCreditProgramUsed();
		}
		
		CreditOfTerm creditOfTerm = new CreditOfTerm();
		
		creditOfTerm.setCreditMin(Integer.parseInt(creditMin));
		creditOfTerm.setCreditMax(Integer.parseInt(creditMax));
		creditOfTerm.setProgram(program);
		
		creditOfTermService.saveCreditOfTerm(creditOfTerm);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลเกณฑ์หน่วยกิต");
		logService.saveLog(log);
		
	}
	
	public void updateCreditOfTerm(CreditOfTermUpdatePayload body) throws ProgramException, CreditOfTermException, LogException  {
		CreditOfTerm creditOfTerm = creditOfTermService.findCreditOfTermByUuid(body.getCreditOfTermUid());
		String creditMin = body.getCreditMin();
		String creditMax = body.getCreditMax();
		
		if(creditMin == null || creditMin.equals("")) {
			throw CreditOfTermException.creditOfTermCreditMinEmpty();
		}
		
		if(creditMax == null || creditMax.equals("")) {
			throw CreditOfTermException.creditOfTermCreditMaxEmpty();
		}
		if(Integer.parseInt(creditMin) > Integer.parseInt(creditMax)) {
			throw CreditOfTermException.creditIncorrect();
		}
		
		creditOfTerm.setCreditMin(Integer.parseInt(creditMin));
		creditOfTerm.setCreditMax(Integer.parseInt(creditMax));
		
		creditOfTermService.saveCreditOfTerm(creditOfTerm);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลเกณฑ์หน่วยกิต");
		logService.saveLog(log);
	}
	
	
}
