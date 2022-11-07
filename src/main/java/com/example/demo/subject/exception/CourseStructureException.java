package com.example.demo.subject.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.BaseException;

public class CourseStructureException extends BaseException {
	private static final long serialVersionUID = -3371503320561323817L;

    protected CourseStructureException(String code) {
        super("courseStructure."+code);
    }
    
	protected CourseStructureException(String code, HttpStatus status) {
        super("courseStructure."+code, status);
    }
	
	public static CourseStructureException courseStructureUidEmpty() {
		return new CourseStructureException("courseStructureUid.empty", HttpStatus.BAD_REQUEST);
	}
	public static CourseStructureException courseStructureNameEmpty() {
		return new CourseStructureException("courseStructureName.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureException courseStructureGeCreditsEmpty() {
		return new CourseStructureException("courseStructureGeCredits.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureException courseStructureMajorCreditsEmpty() {
		return new CourseStructureException("courseStructureMajorCredits.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureException courseStructureProgramUsed() {
		return new CourseStructureException("courseStructureProgram.used", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureException courseStructureNameUsed() {
		return new CourseStructureException("courseStructureName.used", HttpStatus.BAD_REQUEST);
	}
	public static CourseStructureException courseStructureCourseUidEmpty() {
		return new CourseStructureException("courseStructureCourseUid.empty", HttpStatus.BAD_REQUEST);
	}
	
	public static CourseStructureException courseStructureNotFound() {
        return new CourseStructureException("notFound",HttpStatus.EXPECTATION_FAILED);
    }
	public static CourseStructureException courseStructureIsEmptyOrNull() {
        return new CourseStructureException("emptyOrNull",HttpStatus.EXPECTATION_FAILED);
    }
}
