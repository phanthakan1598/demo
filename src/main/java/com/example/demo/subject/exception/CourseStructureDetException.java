package com.example.demo.subject.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CourseStructureDetException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CourseStructureDetException(String code) {
        super("courseStructureDet."+code);
    }
    
	protected CourseStructureDetException(String code, HttpStatus status) {
        super("courseStructureDet."+code, status);
    }
	
	public static CourseStructureDetException courseStructureDetUidEmpty() {
		return new CourseStructureDetException("courseStructureDetUid.empty", HttpStatus.BAD_REQUEST);
	}
	public static CourseStructureDetException courseStructureDetNameEmpty() {
		return new CourseStructureDetException("courseStructureDetName.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureDetException courseStructureDetGeCreditsEmpty() {
		return new CourseStructureDetException("courseStructureDetGeCredits.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureDetException courseStructureDetMajorCreditsEmpty() {
		return new CourseStructureDetException("courseStructureDetMajorCredits.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureDetException courseStructureDetSubjectUsed() {
		return new CourseStructureDetException("courseStructureDetSubject.used", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureDetException courseStructureDetNameUsed() {
		return new CourseStructureDetException("courseStructureDetName.used", HttpStatus.BAD_REQUEST);
	}
	public static CourseStructureDetException courseStructureDetCourseUidEmpty() {
		return new CourseStructureDetException("courseStructureDetCourseUid.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureDetException courseStructureDetNotFound() {
        return new CourseStructureDetException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CourseStructureDetException courseStructureDetIsEmptyOrNull() {
        return new CourseStructureDetException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
