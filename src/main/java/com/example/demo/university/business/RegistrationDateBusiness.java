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
import com.example.demo.university.exception.RegistrationDateException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.json.RegistrationDateAllJson;
import com.example.demo.university.model.Calendar;
import com.example.demo.university.model.RegistrationDate;
import com.example.demo.university.model.Term;
import com.example.demo.university.payload.registrationDate.RegistrationDateInsertPayload;
import com.example.demo.university.payload.registrationDate.RegistrationDateUpdatePayload;
import com.example.demo.university.service.CalendarService;
import com.example.demo.university.service.RegistrationDateService;
import com.example.demo.university.service.TermService;

import lombok.Setter;

@Service
@Setter
public class RegistrationDateBusiness {
	
	@Autowired
	RegistrationDateService registrationDateService;
	
	@Autowired
	TermService termService;
	
	@Autowired
	CalendarService calendarService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public Boolean registrationDateBoolean(String termUid, String levelClass, String date) throws TermException {
		Term term = termService.findTermByUuid(termUid);
		int registrationDateLevelClass = Integer.parseInt(levelClass);
		LocalDate dateNow = LocalDate.parse(date);
		Boolean registrationDateBoolean = null;
		if (registrationDateService.registrationDateTermBoolean(term, registrationDateLevelClass, dateNow, dateNow) || registrationDateService.registrationDateTermBoolean(term, 0, dateNow, dateNow)) {
			registrationDateBoolean = true;
		}else {
			registrationDateBoolean = false;
		}
		return registrationDateBoolean;
	}
	
	public List<RegistrationDateAllJson> viewRegistrationDateAll (String uuid) throws TermException{
		Term term = termService.findTermByUuid(uuid);
		return  RegistrationDateAllJson.registrationDateCourseAllJsons(registrationDateService.registrationDates(term));
	}
	
	public RegistrationDateAllJson viewRegistrationDate(String uid) throws RegistrationDateException {
		return RegistrationDateAllJson.packRegistrationDatePersonalAllJson(registrationDateService.findRegistrationDateByUuid(uid));
	}

	
	public void insertRegistrationDate(RegistrationDateInsertPayload body) throws TermException ,RegistrationDateException, CalendarException, LogException{
		String levelClass = body.getLevelClass();
		String type = body.getType();
		String range = body.getRange();
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		Term term = termService.findTermByUuid(body.getTermUid());
		
		LocalDate beginDateOld = term.getBeginDate();
		LocalDate endDateOld = term.getEndDate();
		
		if(type == null || type.equals("")) {
			throw RegistrationDateException.typeEmpty();
		}
		
		String name = null;
		if(type.equals("Nomal")) {
			name = "ลงทะเบียนชั้นปี "+levelClass+ " (ช่วงที่ "+range+")";
		}else {
			name = "ลงทะเบียนทุกชั้นปี (ช่วงที่ "+range+")";
		}
		
		if(registrationDateService.registrationDateNameBoolean(name, term)) {
			throw RegistrationDateException.registrationDateUsed();
		}
		
		boolean timeBegin = registrationDateService.timeBeginBoolean(Integer.parseInt(levelClass), term, beginDate, endDate);
		boolean timeEnd = registrationDateService.timeEndBoolean(Integer.parseInt(levelClass), term, beginDate, endDate);
		
		if(timeBegin || timeEnd) {
			throw TermException.termTimeUsed();
		}
		
		
		if (beginDate.isBefore(beginDateOld) || endDate.isBefore(beginDateOld) || 
			beginDate.isAfter(endDateOld) || endDate.isAfter(endDateOld)) {
			throw TermException.termIncorrect();
		}
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		
		if(range == null || range.equals("")) {
			throw RegistrationDateException.registrationDateRangeEmpty();
		}
		
		if(levelClass == null || levelClass.equals("")) {
			throw RegistrationDateException.levelClassEmpty();
		}
		
		if(beginDate == null || Objects.isNull(beginDate)) {
			throw RegistrationDateException.registrationDateBeginDateEmpty();
		}
		
		if(endDate == null || Objects.isNull(endDate)) {
			throw RegistrationDateException.registrationDateEndDateEmpty();
		}
		if(Integer.parseInt(range) > 3) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}		
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
		}else if (Integer.parseInt(range) > 2 && Integer.parseInt(range) < 4) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}else if (Integer.parseInt(range) > 1 && Integer.parseInt(range) < 3) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}else {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate.getBeginDate();
				LocalDate regEndDateOld = registrationDate.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}
		
		RegistrationDate registrationDate = new RegistrationDate();
		
		registrationDate.setRegistrationDateName(name);
		registrationDate.setRegistrationDateLevelClass(Integer.parseInt(levelClass));
		registrationDate.setRegistrationDateRange(Integer.parseInt(range));
		registrationDate.setBeginDate(beginDate);
		registrationDate.setEndDate(endDate);
		registrationDate.setTerm(term);
		registrationDate.setRegistrationDateType(type);
		
		registrationDateService.saveRegistrationDate(registrationDate);
		
		Calendar calendar = new Calendar();
		
		calendar.setName(name);
		calendar.setBeginDate(beginDate);
		calendar.setEndDate(endDate);
		calendar.setTerm(term);
		
		calendarService.saveCalendar(calendar);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลวันลงทะเบียนเรียน");
		logService.saveLog(log);
	}
	
	public void updateRegistrationDate(RegistrationDateUpdatePayload body) throws RegistrationDateException, CalendarException, TermException, LogException {
		RegistrationDate registrationDate = registrationDateService.findRegistrationDateByUuid(body.getRegistrationDateUuid());
		String levelClass = body.getLevelClass();
		String range = body.getRange();
		LocalDate beginDate = body.getBeginDate();
		LocalDate endDate = body.getEndDate();
		Term term = registrationDate.getTerm();
		String type = body.getType();
		
		LocalDate beginDateOld = registrationDate.getBeginDate();
		LocalDate endDateOld = registrationDate.getEndDate();
		
		LocalDate termBeginDateOld = term.getBeginDate();
		LocalDate termEndDateOld = term.getEndDate();
		
		String nameOld = registrationDate.getRegistrationDateName();
		String name = null;
		if(type.equals("Nomal")) {
			name = "ลงทะเบียนชั้นปี "+levelClass+ " (ช่วงที่ "+range+")";
		}else {
			name = "ลงทะเบียนทุกชั้นปี (ช่วงที่ "+range+")";
		}
		
		if(!nameOld.equals(name)) {
			if(registrationDateService.registrationDateNameBoolean(name, term)) {
				throw RegistrationDateException.registrationDateUsed();
			}
		}
		
		if(!beginDateOld.equals(beginDate) && !endDateOld.equals(endDate) ) {
			boolean timeBegin = registrationDateService.timeBeginBoolean(Integer.parseInt(levelClass), term, beginDate, endDate);
			boolean timeEnd = registrationDateService.timeEndBoolean(Integer.parseInt(levelClass), term, beginDate, endDate);
			
			if(timeBegin || timeEnd) {
				throw TermException.termTimeUsed();
			}
		}
		
		if (beginDate.isBefore(termBeginDateOld) || endDate.isBefore(termBeginDateOld) || 
			beginDate.isAfter(termEndDateOld) || endDate.isAfter(termEndDateOld)) {
			throw TermException.termIncorrect();
		}
		if(beginDate.isAfter(endDate) || endDate.isBefore(beginDate)) {
			throw TermException.termIncorrect();
		}
		
		if(range == null || range.equals("")) {
			throw RegistrationDateException.registrationDateRangeEmpty();
		}
		
		if(levelClass == null || levelClass.equals("")) {
			throw RegistrationDateException.levelClassEmpty();
		}
		
		if(beginDate == null || Objects.isNull(beginDate)) {
			throw RegistrationDateException.registrationDateBeginDateEmpty();
		}
		
		if(endDate == null || Objects.isNull(endDate)) {
			throw RegistrationDateException.registrationDateEndDateEmpty();
		}
		if(Integer.parseInt(range) > 3) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate1 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate1.getBeginDate();
				LocalDate regEndDateOld = registrationDate1.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate2 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate2.getBeginDate();
				LocalDate regEndDateOld = registrationDate2.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}		
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate3 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate3.getBeginDate();
				LocalDate regEndDateOld = registrationDate3.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
		}else if (Integer.parseInt(range) > 2 && Integer.parseInt(range) < 4) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate1 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate1.getBeginDate();
				LocalDate regEndDateOld = registrationDate1.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate2 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate2.getBeginDate();
				LocalDate regEndDateOld = registrationDate2.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate4 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate4.getBeginDate();
				LocalDate regEndDateOld = registrationDate4.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}else if (Integer.parseInt(range) > 1 && Integer.parseInt(range) < 3) {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),1, term)){
				RegistrationDate registrationDate1 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 1, term);
				LocalDate regBeginDateOld = registrationDate1.getBeginDate();
				LocalDate regEndDateOld = registrationDate1.getEndDate();
				if (beginDate.isBefore(regBeginDateOld) || endDate.isBefore(regEndDateOld)) {
					throw TermException.termIncorrect();
				}
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate3 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate3.getBeginDate();
				LocalDate regEndDateOld = registrationDate3.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate4 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate4.getBeginDate();
				LocalDate regEndDateOld = registrationDate4.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}else {
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),2, term)){
				RegistrationDate registrationDate2 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 2, term);
				LocalDate regBeginDateOld = registrationDate2.getBeginDate();
				LocalDate regEndDateOld = registrationDate2.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),3, term)){
				RegistrationDate registrationDate3 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 3, term);
				LocalDate regBeginDateOld = registrationDate3.getBeginDate();
				LocalDate regEndDateOld = registrationDate3.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}			
			}
			if(registrationDateService.registrationDateRangeAndTermBoolean(Integer.parseInt(levelClass),4, term)){
				RegistrationDate registrationDate4 = registrationDateService.findRegistrationDateLevel(Integer.parseInt(levelClass), 4, term);
				LocalDate regBeginDateOld = registrationDate4.getBeginDate();
				LocalDate regEndDateOld = registrationDate4.getEndDate();
				if (beginDate.isAfter(regBeginDateOld) || endDate.isAfter(regEndDateOld)) {
					throw TermException.termIncorrect();
				}	
			}
		}
		
		registrationDate.setRegistrationDateName(name);
		registrationDate.setRegistrationDateLevelClass(Integer.parseInt(levelClass));
		registrationDate.setRegistrationDateRange(Integer.parseInt(range));
		registrationDate.setBeginDate(beginDate);
		registrationDate.setEndDate(endDate);
		registrationDate.setRegistrationDateType(type);
		registrationDate.setTerm(term);
		
		registrationDateService.saveRegistrationDate(registrationDate);
		
		Calendar calendar = calendarService.findCalendarByName(nameOld, term);
		
		calendar.setName(name);
		calendar.setBeginDate(beginDate);
		calendar.setEndDate(endDate);
		
		calendarService.saveCalendar(calendar);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลวันลงทะเบียนเรียน");
		logService.saveLog(log);
	}
	
	public void deleteRegistrationDate(String uuid) throws RegistrationDateException, CalendarException, LogException {
		RegistrationDate registrationDate = registrationDateService.findRegistrationDateByUuid(uuid);
		Calendar calendar = calendarService.findCalendarByName(registrationDate.getRegistrationDateName(),registrationDate.getTerm());
		calendarService.delCalendar(calendar);
		registrationDateService.delRegistrationDate(registrationDate);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลวันลงทะเบียนเรียน");
		logService.saveLog(log);
		
	}

}
