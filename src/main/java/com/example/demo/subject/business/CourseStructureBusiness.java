package com.example.demo.subject.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.CourseStructureException;
import com.example.demo.subject.json.CourseStructureAllJson;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.model.CourseStructureDet;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.payload.courseStructure.CourseStructureInsertPayload;
import com.example.demo.subject.payload.courseStructure.CourseStructureUpdatePayload;
import com.example.demo.subject.service.CourseStructureDetService;
import com.example.demo.subject.service.CourseStructureService;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class CourseStructureBusiness {
	
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
	CourseStructureDetService courseStructureDetService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	public List<CourseStructureAllJson> viewAllCourseStructure (String degreeLevelUid, String facUid) throws DegreeLevelException, FacultyException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeLevelUid);
		Faculty faculty = facultyService.findFacultyByUuid(facUid);
		return CourseStructureAllJson.courseStructureCourseAllJsons(courseStructureService.courseStructures(degreeLevel ,faculty));
	}
	
	public CourseStructureAllJson viewCourseStructure (String uid) throws CourseStructureException{
		return CourseStructureAllJson.packCourseStructurePersonalAllJson(courseStructureService.findCourseStructureByUuid(uid));
	}
	
	public void insertCourseStructure(CourseStructureInsertPayload body) throws ProgramException, CourseStructureException, CourseStructureDetException, LogException{
		String name = body.getName();
		Program program = programService.findProgramByUuid(body.getProgramUuid());
		DegreeLevel degreeLevel = program.getDegreeLevel();
		Faculty faculty = program.getFaculty();
		String courseStructureGeCredits = body.getCourseStructureGeCredits();
		String courseStructureMajorCredits = body.getCourseStructureMajorCredits();
		
		if(name == null || name.equals("")) {
			throw CourseStructureException.courseStructureNameEmpty();
		}
		
		if(courseStructureGeCredits == null || courseStructureGeCredits.equals("")) {
			throw CourseStructureException.courseStructureGeCreditsEmpty();
		}
		
		if(courseStructureMajorCredits == null || courseStructureMajorCredits.equals("")) {
			throw CourseStructureException.courseStructureMajorCreditsEmpty();
		}
		
		if(courseStructureService.booleanProgram(program)) {
			throw CourseStructureException.courseStructureProgramUsed();
		}
		
		if(courseStructureService.booleanName(faculty, name)) {
			throw CourseStructureException.courseStructureNameUsed();
		}
		
		CourseStructure courseStructure = new CourseStructure();
		courseStructure.setName(name);
		courseStructure.setProgram(program);
		courseStructure.setCourseStructureGeCredits(Integer.parseInt(courseStructureGeCredits));
		courseStructure.setCourseStructureMajorCredits(Integer.parseInt(courseStructureMajorCredits));
		courseStructureService.saveCourseStructure(courseStructure);
		
		List<Subject> subjectGes = subjectService.subjectGes(degreeLevel);
		for (Subject subject : subjectGes) {
			CourseStructureDet courseStructureDet = new CourseStructureDet();
			courseStructureDet.setCourseStructure(courseStructure);
			courseStructureDet.setSubject(subject);
			courseStructureDetService.saveCourseStructureDet(courseStructureDet);
			
		}
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลโครงสร้างหลักสูตร");
		logService.saveLog(log);
		
	}
	
	public void updateCourseStructure(CourseStructureUpdatePayload body)throws CourseStructureException, LogException {
		CourseStructure courseStructure = courseStructureService.findCourseStructureByUuid(body.getCourseStructureUid());
		Faculty faculty =courseStructure.getProgram().getFaculty();
		
		String nameOld = courseStructure.getName();
		String name = body.getName();
		String courseStructureGeCredits = body.getCourseStructureGeCredits();
		String courseStructureMajorCredits = body.getCourseStructureMajorCredits();
		
		if(name == null || name.equals("")) {
			throw CourseStructureException.courseStructureNameEmpty();
		}
		
		if(courseStructureGeCredits == null || courseStructureGeCredits.equals("")) {
			throw CourseStructureException.courseStructureGeCreditsEmpty();
		}
		
		if(courseStructureMajorCredits == null || courseStructureMajorCredits.equals("")) {
			throw CourseStructureException.courseStructureMajorCreditsEmpty();
		}
		
		if(!name.equals(nameOld)) {
			if(courseStructureService.booleanName(faculty, name)) {
				throw CourseStructureException.courseStructureNameUsed();
			}
		}
		courseStructure.setName(name);
		courseStructure.setCourseStructureGeCredits(Integer.parseInt(courseStructureGeCredits));
		courseStructure.setCourseStructureMajorCredits(Integer.parseInt(courseStructureMajorCredits));
		courseStructureService.saveCourseStructure(courseStructure);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลโครงสร้างหลักสูตร");
		logService.saveLog(log);
	}
	
	public void delCourseStructure(String uid)throws CourseStructureException, LogException {
		CourseStructure courseStructure = courseStructureService.findCourseStructureByUuid(uid);
		courseStructureService.delCourseStructure(courseStructure);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลโครงสร้างหลักสูตร");
		logService.saveLog(log);
	}
}
