package com.example.demo.member.business;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.course.model.StudyProgramDet;
import com.example.demo.course.service.StudyProgramDetService;
import com.example.demo.faculty.model.Program;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.json.student.TermClassLevelAllJson;
import com.example.demo.member.model.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.register.json.StudyRegisterStudentSubjectAllJson;
import com.example.demo.register.model.EnrollRegister;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.register.service.EnrollRegisterService;
import com.example.demo.register.service.StudyRegisterService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.service.TermService;

import lombok.Setter;

@Service
@Setter
public class MemberStudentBusiness {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	TermService termService;
	
	@Autowired
	StudyProgramDetService studyProgramDetService;
	
	@Autowired
	StudyRegisterService studyRegisterService;
	
	@Autowired
	EnrollRegisterService enrollRegisterService;
	
	
	public List<StudyRegisterStudentSubjectAllJson> viewSubjectTerm (String termUid) throws MemberException, TermException {
		Program program = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getProgram();
		Term term = termService.findTermByUuid(termUid);
		
		Member member = jwtAuth.getCurrentMember();
		List<String>  types = new ArrayList<>();
		
		List<StudyRegister> studyRegisteriePrograms = new ArrayList<>();
		List<StudyProgramDet> studyProgramDets = studyProgramDetService.studyProSub(program);
		
		List<StudyRegister> studyRegisteries = studyRegisterService.studyRegisterTerm(term);
		
		String type = null;
		
//		System.out.println(enrollRegisterService.booleanEnrollRegisterMember(member));
//		if(enrollRegisterService.booleanEnrollRegisterMember(member)) {
//			System.out.println("term : "+  term.getTerm());
//		}
		
		if(enrollRegisterService.booleanEnrollRegisterMember(member)) {
			
			List<EnrollRegister> enrollRegisters = enrollRegisterService.enrollRegisterMembers(member);
			for (EnrollRegister enrollRegister : enrollRegisters) {
				int index = 0;
				for (StudyRegister studyRegister : studyRegisteries) {
					for (StudyProgramDet studyProgramDet : studyProgramDets) {
						if(studyRegister.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
							if(enrollRegister.getStudyRegister().getSubject().getSubjectCode().equals(studyRegister.getSubject().getSubjectCode()) ) {
								type = "used";
								if(types.size() == index) {
									types.add(type);
								}else {
									types.set(index, type);
								}
								index++;
							}else {
								type = "nomal";
								if(types.size() == index) {
									types.add(type);
								}else {
									if(types.get(index).equals("used")) {
										type = "used";
									}
									types.set(index, type);
								}
								index++;
							}
							
						}else {
							
							if(studyRegister.getSubject().getSubjectCode().equals(studyProgramDet.getSubjectCode())) {
								if(enrollRegister.getStudyRegister().getSubject().getSubjectCode().equals(studyRegister.getSubject().getSubjectCode()) ) {
									type = "used";
									if(types.size() == index) {
										types.add(type);
									}else {
										types.set(index, type);
									}
									index++;
								}else {
									type = "nomal";
									if(types.size() == index) {
										types.add(type);
									}else {
										if(types.get(index).equals("used")) {
											type = "used";
										}
										types.set(index, type);
									}
									index++;
								}
								
							}
						}
					}
					
				
				}
			}
				
			for (StudyRegister studyRegister : studyRegisteries) {
				if(studyRegister.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
					studyRegisteriePrograms.add(studyRegister);
				}
				for (StudyProgramDet studyProgramDet : studyProgramDets) {
					if(studyRegister.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
					}else {
						if(studyRegister.getSubject().getSubjectCode().equals(studyProgramDet.getSubjectCode())) {
							studyRegisteriePrograms.add(studyRegister);
						}
					}
				}
			}
			
			
		}else {
			for (StudyRegister studyRegisterie : studyRegisteries) {
				for (StudyProgramDet studyProgramDet : studyProgramDets) {
					type = "normal";
					types.add(type);
					if(studyRegisterie.getSubject().getGroupSubject().getTypeSubject().getTypeSubjectNameEn().equals("GE")) {
						studyRegisteriePrograms.add(studyRegisterie);
					}else {
						if(studyRegisterie.getSubject().getSubjectCode().equals(studyProgramDet.getSubjectCode())) {
							studyRegisteriePrograms.add(studyRegisterie);
						}
					}
				}
			}
		}
		
		return StudyRegisterStudentSubjectAllJson.studyRegisterCourseAllJsons(studyRegisteriePrograms,types);
	}


	
	public TermClassLevelAllJson viewAllTermClassLevel(String date) throws MemberException, TermException, ParseException{
		DegreeLevel degreeLevel = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getProgram().getDegreeLevel();
		String yearCreateMember = Integer.toString(memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getCreatedAt().getYear() + 543) ;
		String levelClass = null;
		LocalDate dateNow = LocalDate.parse(date);
		String yearTerm = "";
		Term term = null;
		
		if(termService.booleanTermStudent(degreeLevel, dateNow, dateNow)) {
			term = termService.findTermStudent(degreeLevel, dateNow, dateNow);	
			yearTerm = Integer.toString(term.getYear());
			levelClass = Integer.toString( ((Integer.parseInt(yearTerm) - Integer.parseInt(yearCreateMember)) +1 ));
		}else {
			yearTerm = Integer.toString(dateNow.getYear());
			levelClass = Integer.toString( ((Integer.parseInt(yearTerm) - Integer.parseInt(yearCreateMember)) +1 ));
			term = new Term();
			term.setTermUuid("not term");
			term.setTerm(1);
			term.setYear(Integer.parseInt(yearTerm));
		}
		
		
		
		return TermClassLevelAllJson.packTermClassLevelAllJson(term, levelClass );
//		return  MemberTeacherAllJson.packMemberTeacherAllJson(memberService.getAllTeacherFaculty(faculty));
	}
	
}
