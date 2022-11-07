package com.example.demo.university.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CalendarException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CalendarException(String code) {
        super("calendar."+code);
    }
    
	protected CalendarException(String code, HttpStatus status) {
        super("calendar."+code, status);
    }
	
	public static CalendarException calendarNotFound() {
        return new CalendarException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CalendarException calendarIsEmptyOrNull() {
        return new CalendarException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
	
	public static CalendarException calendarNameEmpty() {
        return new CalendarException("calendarNameEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CalendarException calendarBeginDateEmpty() {
        return new CalendarException("calendarBeginDateEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CalendarException calendarEndDateEmpty() {
        return new CalendarException("calendarEndDateEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CalendarException calendarYearEmpty() {
        return new CalendarException("calendarYearEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CalendarException calendarTermEmpty() {
        return new CalendarException("calendarTermEmpty.empty",HttpStatus.BAD_REQUEST);
    }
	
	public static CalendarException calendarDegreeLevelUidEmpty() {
        return new CalendarException("calendarDegreeLevelUidEmpty.empty",HttpStatus.BAD_REQUEST);
    }
}
