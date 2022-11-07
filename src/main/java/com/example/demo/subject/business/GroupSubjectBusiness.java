package com.example.demo.subject.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.GroupSubjectException;
import com.example.demo.subject.exception.TypeSubjectException;
import com.example.demo.subject.json.GroupSubjectAllJson;
import com.example.demo.subject.json.TypeSubjectAllJson;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.TypeSubject;
import com.example.demo.subject.payload.groupSubject.GroupSubjectInsertPayload;
import com.example.demo.subject.payload.groupSubject.GroupSubjectUpdatePayload;
import com.example.demo.subject.service.GroupSubjectService;
import com.example.demo.subject.service.TypeSubjectService;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class GroupSubjectBusiness {
	
	@Autowired
	GroupSubjectService groupSubjectService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	TypeSubjectService typeSubjectService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public List<TypeSubjectAllJson> viewAllTypeSubject (){
		return TypeSubjectAllJson.typeSubjectCourseAllJsons(typeSubjectService.typeSubjects());
	}
	
	public List<GroupSubjectAllJson> viewAllGroupSubjectMajor (String degreeLevelUid, String facUid) throws DegreeLevelException, FacultyException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeLevelUid);
		Faculty faculty = facultyService.findFacultyByUuid(facUid);
		return GroupSubjectAllJson.groupSubjectCourseAllJsons(groupSubjectService.groupSubjects("MAJOR", degreeLevel ,faculty));
		
//		System.out.println("sssssssss");
//		
//		return null;
	}
	
	public List<GroupSubjectAllJson> viewAllGroupSubjectGe (String degreeLevelUid) throws DegreeLevelException, FacultyException{
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(degreeLevelUid);
		Faculty faculty = facultyService.findFacultyCodeName(jwtAuth.getCurrentUser().getUniversity(), "GE");
		return GroupSubjectAllJson.groupSubjectCourseAllJsons(groupSubjectService.groupSubjects("GE", degreeLevel, faculty));
	}
	
	public GroupSubjectAllJson viewGroupSubject (String uid) throws GroupSubjectException{
		return GroupSubjectAllJson.packGroupSubjectPersonalAllJson(groupSubjectService.findGroupSubjectByUuid(uid));
	}
	
	public void insertGroupSubject(GroupSubjectInsertPayload body)throws GroupSubjectException, DegreeLevelException, TypeSubjectException, FacultyException, LogException {
		String name = body.getName();
		Faculty faculty = null;
		TypeSubject typeSubject = typeSubjectService.findTypeSubjectByUuid(body.getTypeSubjectUuid());
		DegreeLevel degreeLevel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
		
		if(!typeSubject.getTypeSubjectNameEn().equals("GE")) {
			faculty = facultyService.findFacultyByUuid(body.getFacultyUuid());
		}else {
			faculty = facultyService.findFacultyCodeName(jwtAuth.getCurrentUser().getUniversity(), "GE");
		}
		
		if(name == null || name.equals("")) {
			throw GroupSubjectException.groupSubjectNameEmpty();
		}
		
		if(groupSubjectService.booleanName(name, typeSubject, degreeLevel, faculty)){
			throw GroupSubjectException.groupSubjectNameUsed();
		}
		
		GroupSubject groupSubject = new GroupSubject();
		groupSubject.setName(name);
		groupSubject.setFaculty(faculty);
		groupSubject.setDegreeLevel(degreeLevel);
		groupSubject.setTypeSubject(typeSubject);
		groupSubjectService.saveGroupSubject(groupSubject);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลหมวดวิชา");
		logService.saveLog(log);
	}
	
	public void updateGroupSubject(GroupSubjectUpdatePayload body)throws GroupSubjectException, LogException {
		GroupSubject groupSubject = groupSubjectService.findGroupSubjectByUuid(body.getGroupSubjectUid());
		TypeSubject typeSubject = groupSubject.getTypeSubject();
		DegreeLevel degreeLevel = groupSubject.getDegreeLevel();
		Faculty faculty = groupSubject.getFaculty();
		
		String nameOld = groupSubject.getName();
		String name = body.getName();
		
		if(name == null || name.equals("")) {
			throw GroupSubjectException.groupSubjectNameEmpty();
		}
		
		if(!name.equals(nameOld)) {
			if(groupSubjectService.booleanName(name, typeSubject, degreeLevel, faculty)) {
				throw GroupSubjectException.groupSubjectNameUsed();
			}
		}
		
		groupSubject.setName(name);
		groupSubjectService.saveGroupSubject(groupSubject);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลหมวดวิชา");
		logService.saveLog(log);
	}
	
	public void delGroupSubject(String uid)throws GroupSubjectException, LogException {
		GroupSubject groupSubject = groupSubjectService.findGroupSubjectByUuid(uid);
		groupSubjectService.delGroupSubject(groupSubject);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลหมวดวิชา");
		logService.saveLog(log);
	}
}
