package com.example.demo.course.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.json.StudyProgramAllJson;
import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.payload.studyProgram.StudyProgramInsertPayload;
import com.example.demo.course.payload.studyProgram.StudyProgramUpdatePayload;
import com.example.demo.course.service.StudyProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.service.CourseStructureService;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class StudyProgramBusiness {
	
	@Autowired
	StudyProgramService studyProgramService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	CourseStructureService courseStructureService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	public List<StudyProgramAllJson> viewAllStudyProgram(String uid) throws DegreeLevelException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(uid);
		return StudyProgramAllJson.studyProgramStudyProgramAllJsons(studyProgramService.studyPrograms(degreeLevel));
	}
	
	public StudyProgramAllJson viewStudyProgram(String uid) throws StudyProgramException  {
		StudyProgram studyProgram = studyProgramService.findStudyProgramByUuid(uid);
		return StudyProgramAllJson.packStudyProgramPersonalAllJson(studyProgram);
	}
	
	public void insertStudyProgram(StudyProgramInsertPayload body) throws StudyProgramException, CourseStructureException, LogException {
		String name = body.getName();
		CourseStructure courseStructure = courseStructureService.findCourseStructureByUuid(body.getCourseStructureUuid());
		
		if(name == null || name.equals("")) {
			throw StudyProgramException.studyProgramNameEmpty();
		}
		if(studyProgramService.booleanStudyProgramName(name,courseStructure)) {
			throw StudyProgramException.studyProgramNameUsed();
		}
		if(studyProgramService.booleanStudyProgram(courseStructure)) {
			throw StudyProgramException.studyProgramUsed();
		}
		
		StudyProgram studyProgram = new StudyProgram();
		studyProgram.setName(name);
		studyProgram.setCourseStructure(courseStructure);
		studyProgramService.saveStudyProgram(studyProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลแผนเรียนในหลักสูตร");
		logService.saveLog(log);
	}
	
	public void updateStudyProgram(StudyProgramUpdatePayload body) throws StudyProgramException, LogException {
		StudyProgram studyProgram = studyProgramService.findStudyProgramByUuid(body.getStudyProgramUid());
		String name = body.getName();
		String nameOld = studyProgram.getName();
		CourseStructure courseStructure = studyProgram.getCourseStructure();
		
		if(name == null || name.equals("")) {
			throw StudyProgramException.studyProgramNameEmpty();
		}
		if(!name.equals(nameOld)) {
			if(studyProgramService.booleanStudyProgramName(name,courseStructure)) {
				throw StudyProgramException.studyProgramNameUsed();
			}
		}
		studyProgram.setName(name);
		studyProgramService.saveStudyProgram(studyProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลแผนเรียนในหลักสูตร");
		logService.saveLog(log);
	}

	public void deleteStudyProgram(String uuid) throws StudyProgramException, LogException {
		StudyProgram studyProgram = studyProgramService.findStudyProgramByUuid(uuid);
		studyProgramService.delStudyProgram(studyProgram);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลแผนเรียนในหลักสูตร");
		logService.saveLog(log);
	}

}
