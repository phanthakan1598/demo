package com.example.demo.university.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.json.TermAllJson;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.model.University;
import com.example.demo.university.payload.term.TermInsertPayload;
import com.example.demo.university.payload.term.TermUpdatePayload;
import com.example.demo.university.service.DegreeLevelService;
import com.example.demo.university.service.TermService;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class TermBusiness {
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	TermService termService;
	
	@Autowired
	UniversityService universityService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	
	public void insertTerm(TermInsertPayload body) throws TermException, DegreeLevelException, UniversityException, LogException{
		int term = body.getTerm();
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		int year = body.getYear();
		
		if(Objects.isNull(term) || term == 0) {
			throw TermException.termEmpty();
		}if(Objects.isNull(beginDate)) {
			throw TermException.beginDateEmpty();
		}if(Objects.isNull(endDate)) {
			throw TermException.endDateEmpty();
		}if(Objects.isNull(year) || year ==0) {
			throw TermException.yearEmpty();
		}
		
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		
		University university = jwtAuth.getCurrentUser().getUniversity();
		List<DegreeLevel> degreeLevels = degreeLevelService.getAllDegreeLevel(university);
		for (DegreeLevel degreeLevel : degreeLevels) {
			boolean timeBegin = termService.booleanTeamTimeBeginUsed(degreeLevel, year, beginDate, endDate);
			boolean timeEnd = termService.booleanTeamTimeEndDateUsed(degreeLevel, year, beginDate, endDate);
			
			if(timeBegin || timeEnd) {
				throw TermException.termTimeUsed();
			}
			if(termService.booleanTeam(degreeLevel, year, term)){
				throw TermException.termUsed();
			}
			
			if(term > 2) {
				if(termService.booleanTeam(degreeLevel, year, 1)) {
					Term term1check = termService.findTermCheck(degreeLevel, year, 1);
					LocalDate beginDateOld = term1check.getBeginDate();
					LocalDate endDateOld = term1check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
				if (termService.booleanTeam(degreeLevel, year, 2)) {
					Term term2check = termService.findTermCheck(degreeLevel, year, 2);
					LocalDate beginDateOld = term2check.getBeginDate();
					LocalDate endDateOld = term2check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}if (term > 1 && term < 3) {
				if(termService.booleanTeam(degreeLevel, year, 1)) {
					Term term1check = termService.findTermCheck(degreeLevel, year, 1);
					LocalDate beginDateOld = term1check.getBeginDate();
					LocalDate endDateOld = term1check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}if (termService.booleanTeam(degreeLevel, year, 3)) {
					Term term3check = termService.findTermCheck(degreeLevel, year, 3);
					LocalDate beginDateOld = term3check.getBeginDate();
					LocalDate endDateOld = term3check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}if (term < 2) {
				if(termService.booleanTeam(degreeLevel, year, 2)) {
					Term term2check = termService.findTermCheck(degreeLevel, year, 2);
					LocalDate beginDateOld = term2check.getBeginDate();
					LocalDate endDateOld = term2check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}if (termService.booleanTeam(degreeLevel, year, 3)) {
					Term term3check = termService.findTermCheck(degreeLevel, year, 3);
					LocalDate beginDateOld = term3check.getBeginDate();
					LocalDate endDateOld = term3check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}
			
			
			Term termModel = new Term();
			
			termModel.setDegreeLevel(degreeLevel);
			termModel.setTerm(term);
			termModel.setBeginDate(beginDate);
			termModel.setEndDate(endDate);
			termModel.setYear(year);
			
			termService.saveTerm(termModel);
		}
		
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลเทอม");
		logService.saveLog(log);
		
	}
	
//	public void insertTerm(TermInsertPayload body) throws TermException, DegreeLevelException{
//		University university = jwtAuth.getCurrentUser().getUniversity();
//		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
//		int term = body.getTerm();
//		LocalDate beginDate = body.getBeginDate();
//		LocalDate endDate = body.getEndDate();
//		int year = body.getYear();
//		
//		System.out.println("university : "+ university);
//		System.out.println("degreeLevel : "+ degreeLevel);
//		System.out.println("term : "+ term);
//		System.out.println("beginDate : "+ beginDate);
//		System.out.println("endDate : "+ endDate);
//		System.out.println("year : "+ year);
//		
//		boolean timeBegin = termService.booleanTeamTimeBeginUsed(degreeLevel, year, beginDate, endDate);
//		boolean timeEnd = termService.booleanTeamTimeEndDateUsed(degreeLevel, year, beginDate, endDate);
//		
//		if(timeBegin || timeEnd) {
//			throw TermException.termTimeUsed();
//		}
//		if(termService.booleanTeam(degreeLevel, year, term)){
//			throw TermException.termUsed();
//		}
//		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
//			throw TermException.termIncorrect();
//		}
//		if(term > 2) {
//			if(termService.booleanTeam(degreeLevel, year, 1)) {
//				Term term1check = termService.findTermCheck(university, year, 1);
//				LocalDate beginDateOld = term1check.getBeginDate();
//				LocalDate endDateOld = term1check.getEndDate();
//				if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}if (termService.booleanTeam(degreeLevel, year, 2)) {
//				Term term2check = termService.findTermCheck(university, year, 2);
//				LocalDate beginDateOld = term2check.getBeginDate();
//				LocalDate endDateOld = term2check.getEndDate();
//				if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}
//		}else if (term > 1 && term < 3) {
//			if(termService.booleanTeam(degreeLevel, year, 1)) {
//				Term term1check = termService.findTermCheck(university, year, 1);
//				LocalDate beginDateOld = term1check.getBeginDate();
//				LocalDate endDateOld = term1check.getEndDate();
//				if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}if (termService.booleanTeam(degreeLevel, year, 3)) {
//				Term term3check = termService.findTermCheck(university, year, 3);
//				LocalDate beginDateOld = term3check.getBeginDate();
//				LocalDate endDateOld = term3check.getEndDate();
//				if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}
//		}else {
//			if(termService.booleanTeam(degreeLevel, year, 2)) {
//				Term term2check = termService.findTermCheck(university, year, 2);
//				LocalDate beginDateOld = term2check.getBeginDate();
//				LocalDate endDateOld = term2check.getEndDate();
//				if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}else if (termService.booleanTeam(degreeLevel, year, 3)) {
//				Term term3check = termService.findTermCheck(university, year, 3);
//				LocalDate beginDateOld = term3check.getBeginDate();
//				LocalDate endDateOld = term3check.getEndDate();
//				if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
//					throw TermException.termIncorrect();
//				}
//			}
//		}
//		
//		if(Objects.isNull(term) || term == 0) {
//			throw TermException.termEmpty();
//		}if(Objects.isNull(beginDate)) {
//			throw TermException.beginDateEmpty();
//		}if(Objects.isNull(endDate)) {
//			throw TermException.endDateEmpty();
//		}if(Objects.isNull(year) || year ==0) {
//			throw TermException.yearEmpty();
//		}
//		
//		Term termModel = new Term();
//		
//		termModel.setDegreeLevel(degreeLevel);
//		termModel.setTerm(term);
//		termModel.setBeginDate(beginDate);
//		termModel.setEndDate(endDate);
//		termModel.setYear(year);
//		
//		termService.saveTerm(termModel);
//		
//	}
	

//	public void insertTerm(TermInsertPayload body) throws TermException, DegreeLevelException{
////		University university = jwtAuth.getCurrentUser().getUniversity();
//		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
//		int term = body.getTerm();
//		LocalDate beginDate = body.getBeginDate();
//		LocalDate endDate = body.getEndDate();
//		int year = body.getYear();
//		
////		System.out.println("university : "+ university);
//		System.out.println("degreeLevel : "+ degreeLevel);
//		System.out.println("term : "+ term);
//		System.out.println("beginDate : "+ beginDate);
//		System.out.println("endDate : "+ endDate);
//		System.out.println("year : "+ year);
//	
//		
//	}
	
	public void updateTerm(TermUpdatePayload body) throws TermException, LogException {
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		
		if(Objects.isNull(beginDate)) {
			throw TermException.beginDateEmpty();
		}if(Objects.isNull(endDate)) {
			throw TermException.endDateEmpty();
		}
		
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		
		Term termModel = termService.findTermByUuid(body.getTermUid());
		List<Term> terms = termService.listTermUniversity(termModel.getDegreeLevel().getUniversity(), termModel.getYear(), termModel.getTerm());
		for (Term term : terms) {
			int year = termModel.getYear();
			int termNumber = termModel.getTerm();
			long termId = termModel.getTermId();
			DegreeLevel degreeLevel = termModel.getDegreeLevel();
			
			boolean timeBegin = termService.booleanTeamTimeBeginUsedUpdate(termId ,degreeLevel, year, beginDate, endDate);
			boolean timeEnd = termService.booleanTeamTimeEndDateUsedUpdate(termId, degreeLevel, year, beginDate, endDate);
			
			if(timeBegin || timeEnd) {
				throw TermException.termTimeUsed();
			}
			
			if(termNumber > 2) {
				if(termService.booleanTeam(degreeLevel, year, 1)) {
					Term term1check = termService.findTermCheck(degreeLevel, year, 1);
					LocalDate beginDateOld = term1check.getBeginDate();
					LocalDate endDateOld = term1check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}if (termService.booleanTeam(degreeLevel, year, 2)) {
					Term term2check = termService.findTermCheck(degreeLevel, year, 2);
					LocalDate beginDateOld = term2check.getBeginDate();
					LocalDate endDateOld = term2check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}else if (termNumber > 1 && termNumber < 3) {
				if(termService.booleanTeam(degreeLevel, year, 1)) {
					Term term1check = termService.findTermCheck(degreeLevel, year, 1);
					LocalDate beginDateOld = term1check.getBeginDate();
					LocalDate endDateOld = term1check.getEndDate();
					if (beginDate.isBefore(beginDateOld) || endDate.isBefore(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}if (termService.booleanTeam(degreeLevel, year, 3)) {
					Term term3check = termService.findTermCheck(degreeLevel, year, 3);
					LocalDate beginDateOld = term3check.getBeginDate();
					LocalDate endDateOld = term3check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}else {
				if(termService.booleanTeam(degreeLevel, year, 2)) {
					Term term2check = termService.findTermCheck(degreeLevel, year, 2);
					LocalDate beginDateOld = term2check.getBeginDate();
					LocalDate endDateOld = term2check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}else if (termService.booleanTeam(degreeLevel, year, 3)) {
					Term term3check = termService.findTermCheck(degreeLevel, year, 3);
					LocalDate beginDateOld = term3check.getBeginDate();
					LocalDate endDateOld = term3check.getEndDate();
					if (beginDate.isAfter(beginDateOld) || endDate.isAfter(endDateOld)) {
						throw TermException.termIncorrect();
					}
				}
			}
			
			term.setBeginDate(beginDate);
			term.setEndDate(endDate);
			termService.saveTerm(term);
		}
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลเทอม");
		logService.saveLog(log);
		
	}
	
	public void deleteTerm(String uuid) throws TermException, LogException {
		Term termModel = termService.findTermByUuid(uuid);
		List<Term> terms = termService.listTermUniversity(termModel.getDegreeLevel().getUniversity(), termModel.getYear(), termModel.getTerm());
		for (Term term : terms) {
			termService.delTerm(term);
		}
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลเทอม");
		logService.saveLog(log);
	}
	
	public List<TermAllJson> terms (String uuid) throws DegreeLevelException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(uuid);
		return  TermAllJson.termCourseAllJsons(termService.listTerms(degreeLevel)) ;
	}
	
	public TermAllJson term(String uid) throws TermException {
		return TermAllJson.packTermPersonalAllJson(termService.findTermByUuid(uid));
	}

}
