package com.example.demo.university.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.university.exception.CalendarException;
import com.example.demo.university.model.Calendar;
import com.example.demo.university.model.Term;
import com.example.demo.university.repository.CalendarRepository;

import lombok.Setter;

@Service
@Setter
public class CalendarService {
	@Autowired
	CalendarRepository calendarRepository;
	
	public Calendar findCalendarByUuid(String uuid) throws CalendarException {
        return calendarRepository.findOneByCalendarUuid(uuid).orElseThrow(CalendarException::calendarNotFound);
    }
	
	public Calendar findCalendarByName(String name,Term term) throws CalendarException {
        return calendarRepository.findOneByNameAndTerm(name,term).orElseThrow(CalendarException::calendarNotFound);
    }
	
	
	
	public Calendar saveCalendar(Calendar calendar) throws CalendarException {
    	if(Objects.isNull(calendar)) {
    		throw CalendarException.calendarIsEmptyOrNull();
    	}
		return calendarRepository.save(calendar);
	}
	
	public void delCalendar(Calendar calendar) throws CalendarException {
    	if(Objects.isNull(calendar)) {
    		throw CalendarException.calendarIsEmptyOrNull();
    	}
    	calendarRepository.delete(calendar);
	}
	
	public List<Calendar> ListCalendar (Term term){
		return calendarRepository.findByTermOrderByBeginDateAscEndDateAsc(term);
	}
}
