package com.example.demo.course.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.exception.StudyProgramDetException;
import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.json.StudyProgramDetAllJson;
import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.model.StudyProgramDet;
import com.example.demo.course.payload.studyProgramDet.StudyProgramDetInsertPayload;
import com.example.demo.course.payload.studyProgramDet.StudyProgramDetUpdatePayload;
import com.example.demo.course.service.StudyProgramDetService;
import com.example.demo.course.service.StudyProgramService;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.exception.TypeSubjectException;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.model.TypeSubject;
import com.example.demo.subject.service.CourseStructureDetService;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.subject.service.TypeSubjectService;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class StudyProgramDetBusiness {
	
	@Autowired
	StudyProgramDetService studyProgramDetService;
	
	@Autowired
	StudyProgramService studyProgramService;
	
	@Autowired
	TypeSubjectService typeSubjectService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseStructureDetService courseStructureDetService;
	
	@Autowired
	LogService logService;
	
	public List<StudyProgramDetAllJson> viewAllStudyProgramDet(String uid) throws StudyProgramException, SubjectException {
		StudyProgram studyProgram = studyProgramService.findStudyProgramByUuid(uid);
		DegreeLevel degreeLevel = studyProgram.getCourseStructure().getProgram().getDegreeLevel();
		List<StudyProgramDet> studyProgramDets = studyProgramDetService.studyProgramDets(studyProgram);
		List<Subject> subjects = new ArrayList<>();
		
//		Subject subject = subjectService.findSubjectBySubjectCode(subjectCode, degreeLevel);
		for (StudyProgramDet studyProgramDet : studyProgramDets) {
			Subject subject = null;
			if(!studyProgramDet.getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
				subject = subjectService.findSubjectBySubjectCode(studyProgramDet.getSubjectCode(), degreeLevel);
			}else {
				Subject subjectGe = new Subject();
				subjectGe.setCredit(2);
				subjectGe.setName("วิชาทั่วไป");
				subjectGe.setSubjectCode("xx00000");
				subject = subjectGe;
			}
			subjects.add(subject);
		}
		
		return StudyProgramDetAllJson.studyProgramDetStudyProgramDetAllJsons(studyProgramDets , subjects);
	}
	
	public StudyProgramDetAllJson viewStudyProgramDet(String uid) throws StudyProgramDetException, SubjectException  {
		StudyProgramDet studyProgramDet = studyProgramDetService.findStudyProgramDetByUuid(uid);
		DegreeLevel degreeLevel = studyProgramDet.getStudyProgram().getCourseStructure().getProgram().getDegreeLevel();
		Subject subject = null;
		if(!studyProgramDet.getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
			subject = subjectService.findSubjectBySubjectCode(studyProgramDet.getSubjectCode(), degreeLevel);
		}else {
			Subject subjectGe = new Subject();
			subjectGe.setCredit(2);
			subjectGe.setName("วิชาทั่วไป");
			subjectGe.setSubjectCode("xx00000");
			subject = subjectGe;
		}
		return StudyProgramDetAllJson.packStudyProgramDetPersonalAllJson(studyProgramDet,subject);
	}
	
	public void insertStudyProgramDet(StudyProgramDetInsertPayload body) throws TypeSubjectException, StudyProgramException, StudyProgramDetException, SubjectException, LogException  {
		String term = body.getTerm();
		String levelClass = body.getLevelClass();
		TypeSubject typeSubject = typeSubjectService.findTypeSubjectByUuid(body.getTypeSubjectUid());
		StudyProgram studyProgram = studyProgramService.findStudyProgramByUuid(body.getStudyProgramUid());
		String subjectCode = body.getSubjectCode();
		
		if(term == null || term.equals("")) {
			throw StudyProgramDetException.studyProgramDetTermEmpty();
		}
		if(levelClass == null || levelClass.equals("")) {
			throw StudyProgramDetException.studyProgramDetLevelClassEmpty();
		}
		
		if(!typeSubject.getTypeSubjectNameEn().equals("GE")) {
			DegreeLevel degreeLevel = studyProgram.getCourseStructure().getProgram().getDegreeLevel();
			Subject subject = subjectService.findSubjectBySubjectCode(subjectCode, degreeLevel);
			Faculty facultySubject = subject.getGroupSubject().getFaculty();
			Faculty facultyStudyProgram = studyProgram.getCourseStructure().getProgram().getFaculty();
			if(studyProgramDetService.booleanStudySubject(subjectCode, studyProgram)) {
				throw StudyProgramDetException.studyProgramDetUsed();
			}
			
			if(!facultySubject.equals(facultyStudyProgram)) {
				throw StudyProgramDetException.studyProgramDetIncorrect();
			}
		}
		
		StudyProgramDet studyProgramDet = new StudyProgramDet();
		studyProgramDet.setTerm(term);
		studyProgramDet.setLevelClass(Integer.parseInt(levelClass));
		studyProgramDet.setStudyProgram(studyProgram);
		studyProgramDet.setTypeSubject(typeSubject);
		studyProgramDet.setSubjectCode(subjectCode);
		studyProgramDetService.saveStudyProgramDet(studyProgramDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลรายละเอียดแผนเรียนในหลักสูตร");
		logService.saveLog(log);
	}
	
	public void updateStudyProgramDet(StudyProgramDetUpdatePayload body) throws StudyProgramDetException, TypeSubjectException, SubjectException, LogException {
		StudyProgramDet studyProgramDet = studyProgramDetService.findStudyProgramDetByUuid(body.getStudyProgramDetUid());
		String term = body.getTerm();
		String levelClass = body.getLevelClass();
		StudyProgram studyProgram = studyProgramDet.getStudyProgram();
		TypeSubject typeSubject = typeSubjectService.findTypeSubjectByUuid(body.getTypeSubjectUid());
		String subjectCodeOld = studyProgramDet.getSubjectCode();
		String subjectCode = body.getSubjectCode();
		
		if(term == null || term.equals("")) {
			throw StudyProgramDetException.studyProgramDetTermEmpty();
		}
		if(levelClass == null || levelClass.equals("")) {
			throw StudyProgramDetException.studyProgramDetLevelClassEmpty();
		}
		if(!subjectCode.equals(subjectCodeOld)) {
			if(!typeSubject.getTypeSubjectNameEn().equals("GE")) {
				DegreeLevel degreeLevel = studyProgram.getCourseStructure().getProgram().getDegreeLevel();
				Subject subject = subjectService.findSubjectBySubjectCode(subjectCode, degreeLevel);
				Faculty facultySubject = subject.getGroupSubject().getFaculty();
				Faculty facultyStudyProgram = studyProgram.getCourseStructure().getProgram().getFaculty();
				if(studyProgramDetService.booleanStudySubject(subjectCode, studyProgram)) {
					throw StudyProgramDetException.studyProgramDetUsed();
				}
				
				if(!facultySubject.equals(facultyStudyProgram)) {
					throw StudyProgramDetException.studyProgramDetIncorrect();
				}
			}
		}
		
		studyProgramDet.setTerm(term);
		studyProgramDet.setLevelClass(Integer.parseInt(levelClass));
		studyProgramDet.setStudyProgram(studyProgram);
		studyProgramDet.setTypeSubject(typeSubject);
		studyProgramDet.setSubjectCode(subjectCode);
		studyProgramDetService.saveStudyProgramDet(studyProgramDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลรายละเอียดแผนเรียนในหลักสูตร");
		logService.saveLog(log);

	}

	public void deleteStudyProgramDet(String uuid) throws StudyProgramDetException, LogException {
		StudyProgramDet studyProgramDet = studyProgramDetService.findStudyProgramDetByUuid(uuid);
		studyProgramDetService.delStudyProgramDet(studyProgramDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลรายละเอียดแผนเรียนในหลักสูตร");
		logService.saveLog(log);
	}


}
