package com.example.demo.subject.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.json.CourseStructureDetAllJson;
import com.example.demo.subject.json.SubjectCourseStructureAllJson;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.model.CourseStructureDet;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.payload.courseStructureDet.CourseStructureDetInsertPayload;
import com.example.demo.subject.payload.courseStructureDet.CourseStructureDetUpdatePayload;
import com.example.demo.subject.service.CourseStructureDetService;
import com.example.demo.subject.service.CourseStructureService;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class CourseStructureDetBusiness {
	
	@Autowired
	CourseStructureDetService courseStructureDetService;
	
	@Autowired
	CourseStructureService courseStructureService;
	
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public List<SubjectCourseStructureAllJson> viewAllSubjectCourseStructure (String degreeUid) throws CourseStructureException, MemberException, DegreeLevelException, FacultyException {
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeUid);
		String roleName = jwtAuth.getCurrentUser().getRole().getRoleName();
		String type = null;
		Faculty faculty = null;
		if (roleName.equals("ROLE_ACADEMIC_FAC")) {
			type = "MAJOR";
			faculty = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getFaculty();
			return SubjectCourseStructureAllJson.subjectCourseStructureAllJsons(courseStructureDetService.courseStructureSubject(degreeLevel, faculty,type));
			
		}else if (roleName.equals("ROLE_ACADEMIC_GE")) {
			type = "GE";
			faculty = facultyService.findFacultyCodeName(jwtAuth.getCurrentUser().getUniversity(), type);
			return SubjectCourseStructureAllJson.subjectCourseStructureAllJsons(courseStructureDetService.courseStructureSubjectGe(degreeLevel,type));
		}else {
			throw CourseStructureException.courseStructureNotFound();
		}
	}
	
	public List<CourseStructureDetAllJson> viewAllCourseStructureDet (String courseStructureUid) throws CourseStructureException {
		CourseStructure courseStructure = courseStructureService.findCourseStructureByUuid(courseStructureUid);
		return CourseStructureDetAllJson.courseStructureDetCourseAllJsons(courseStructureDetService.courseStructureDets(courseStructure));
	}
	
	public CourseStructureDetAllJson viewCourseStructureDet (String uid) throws CourseStructureDetException{
		return CourseStructureDetAllJson.packCourseStructureDetAllJson(courseStructureDetService.findCourseStructureDetByUuid(uid));
	}
	
	public void insertCourseStructureDet(CourseStructureDetInsertPayload body) throws CourseStructureException, SubjectException, CourseStructureDetException, LogException{
		CourseStructure courseStructure = courseStructureService.findCourseStructureByUuid(body.getCourseStructureUuid());
		Subject subject = subjectService.findSubjectByUuid(body.getSubjectUuid());
		
		if(courseStructureDetService.booleanSubject(subject, courseStructure)) {
			throw CourseStructureDetException.courseStructureDetSubjectUsed();
		}

		CourseStructureDet courseStructureDet = new CourseStructureDet();
		courseStructureDet.setCourseStructure(courseStructure);
		courseStructureDet.setSubject(subject);
		courseStructureDetService.saveCourseStructureDet(courseStructureDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลรายละเอียดโครงสร้างหลักสูตร");
		logService.saveLog(log);
	}
	
	public void updateCourseStructureDet(CourseStructureDetUpdatePayload body)throws CourseStructureDetException, SubjectException, LogException {
		CourseStructureDet courseStructureDet = courseStructureDetService.findCourseStructureDetByUuid(body.getCourseStructureDetUuid());
		Subject subjectOld = courseStructureDet.getSubject();
		Subject subject = subjectService.findSubjectByUuid(body.getSubjectUuid());
		CourseStructure courseStructure = courseStructureDet.getCourseStructure();
		
		if(!subjectOld.getSubjectCode().equals(subject.getSubjectCode())) {
			if(courseStructureDetService.booleanSubject(subject, courseStructure)) {
				throw CourseStructureDetException.courseStructureDetSubjectUsed();
			}
		}
		
		courseStructureDet.setCourseStructure(courseStructure);
		courseStructureDet.setSubject(subject);
		courseStructureDetService.saveCourseStructureDet(courseStructureDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลรายละเอียดโครงสร้างหลักสูตร");
		logService.saveLog(log);
	}
	
	public void delCourseStructureDet(String uid)throws CourseStructureDetException, LogException {
		CourseStructureDet courseStructureDet = courseStructureDetService.findCourseStructureDetByUuid(uid);
		courseStructureDetService.delCourseStructureDet(courseStructureDet);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลรายละเอียดโครงสร้างหลักสูตร");
		logService.saveLog(log);
	}
}
