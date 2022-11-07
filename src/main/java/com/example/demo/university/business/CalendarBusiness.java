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
import com.example.demo.university.exception.CalendarException;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.json.CalendarAllJson;
import com.example.demo.university.json.TermAllJson;
import com.example.demo.university.model.Calendar;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.payload.calendar.CalendarInsertPayload;
import com.example.demo.university.payload.calendar.CalendarUpdatePayload;
import com.example.demo.university.service.CalendarService;
import com.example.demo.university.service.DegreeLevelService;
import com.example.demo.university.service.TermService;

import lombok.Setter;

@Service
@Setter
public class CalendarBusiness {
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	TermService termService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	public List<TermAllJson> viewTerm(String degreeUid,int year) throws DegreeLevelException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeUid);
		return TermAllJson.termCourseAllJsons(termService.listTermDegree(degreeLevel,year));
	}
	
	public List<CalendarAllJson> viewTerm(String uid) throws TermException{
		Term term = termService.findTermByUuid(uid);
		return CalendarAllJson.calendarCourseAllJsons(calendarService.ListCalendar(term));
	}
	
	public void insertCalendar(CalendarInsertPayload body) throws CalendarException, DegreeLevelException, TermException, LogException{
		String name = body.getName();
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		Term term = termService.findTermByUuid(body.getTermUid());
		
		LocalDate beginDateOld = term.getBeginDate();
		LocalDate endDateOld = term.getEndDate();
		
		if (beginDate.isBefore(beginDateOld) || endDate.isBefore(beginDateOld) || 
			beginDate.isAfter(endDateOld) || endDate.isAfter(endDateOld)) {
			throw TermException.termIncorrect();
		}
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		
		if(name == null || name.equals("")) {
			throw CalendarException.calendarNameEmpty();
		}
		
		if(beginDate == null || Objects.isNull(beginDate)) {
			throw CalendarException.calendarBeginDateEmpty();
		}
		
		if(endDate == null || Objects.isNull(endDate)) {
			throw CalendarException.calendarEndDateEmpty();
		}
		
		Calendar calendar = new Calendar();
		
		calendar.setName(name);
		calendar.setBeginDate(beginDate);
		calendar.setEndDate(endDate);
		calendar.setTerm(term);
		
		calendarService.saveCalendar(calendar);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลปฏิทินการศึกษา");
		logService.saveLog(log);
		
	}
	
	public void updateCalendar(CalendarUpdatePayload body) throws DegreeLevelException, CalendarException, TermException, LogException {
		Calendar calendar = calendarService.findCalendarByUuid(body.getCalendarUid());
		String name = body.getName();
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		
		Term term = termService.findTermByUuid(calendar.getTerm().getTermUuid());
		
		LocalDate beginDateOld = term.getBeginDate();
		LocalDate endDateOld = term.getEndDate();
		
		if (beginDate.isBefore(beginDateOld) || endDate.isBefore(beginDateOld) || 
			beginDate.isAfter(endDateOld) || endDate.isAfter(endDateOld)) {
			throw TermException.termIncorrect();
		}
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		if(name == null || name.equals("")) {
			throw CalendarException.calendarNameEmpty();
		}
		
		if(beginDate == null || Objects.isNull(beginDate)) {
			throw CalendarException.calendarBeginDateEmpty();
		}
		
		if(endDate == null || Objects.isNull(endDate)) {
			throw CalendarException.calendarEndDateEmpty();
		}
		
		calendar.setName(name);
		calendar.setBeginDate(beginDate);
		calendar.setEndDate(endDate);

		calendarService.saveCalendar(calendar);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลปฏิทินการศึกษา");
		logService.saveLog(log);
		
	}

	public void deleteCalendar(String uuid) throws CalendarException, LogException{
		Calendar calendar = calendarService.findCalendarByUuid(uuid);
		
		calendarService.delCalendar(calendar);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลปฏิทินการศึกษา");
		logService.saveLog(log);
		
	}
	
//	public List<CalendarAllJson> viewAllCalendar(String degreeUid,String termUid) throws DegreeLevelException, TermException{
//		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeUid);
//		Term term = termService.findTermByUuid(termUid);
//		University university = jwtAuth.getCurrentUser().getUniversity();
//		
//		return CalendarAllJson.calendarCourseAllJsons(calendarService.ListCalendar(university, degreeLevel, term));
//		
//	}
	
	public CalendarAllJson viewCalendar(String uid) throws CalendarException {
		Calendar calendar = calendarService.findCalendarByUuid(uid);
		return CalendarAllJson.packCalendarPersonalAllJson(calendar);
		
	}

}
