package com.example.demo.register.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.exception.BillProgramException;
import com.example.demo.bill.model.BillProgram;
import com.example.demo.bill.service.BillProgramService;
import com.example.demo.building.exception.RoomException;
import com.example.demo.building.model.Room;
import com.example.demo.building.service.RoomService;
import com.example.demo.course.exception.StudyProgramDetException;
import com.example.demo.course.exception.StudyProgramException;
import com.example.demo.course.model.StudyProgram;
import com.example.demo.course.service.StudyProgramDetService;
import com.example.demo.course.service.StudyProgramService;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.register.exception.DayException;
import com.example.demo.register.exception.EnrollRegisterException;
import com.example.demo.register.exception.StudyRegisterException;
import com.example.demo.register.exception.StudyRegisterMemberException;
import com.example.demo.register.json.CountCreditAllJson;
import com.example.demo.register.json.DayAllJson;
import com.example.demo.register.json.StudyRegisterAllJson;
import com.example.demo.register.json.StudyRegisterMemberAllJson;
import com.example.demo.register.json.StudyRegisterStudentJson;
import com.example.demo.register.json.StudyRegisterTeacherJson;
import com.example.demo.register.json.StudyRegisterUidJson;
import com.example.demo.register.model.Day;
import com.example.demo.register.model.EnrollRegister;
import com.example.demo.register.model.StudyRegister;
import com.example.demo.register.model.StudyRegisterMember;
import com.example.demo.register.payload.enrollRegister.EnrollRegisterInsertPayload;
import com.example.demo.register.payload.studyRegister.StudyRegisterInsertPayload;
import com.example.demo.register.payload.studyRegister.StudyRegisterUpdatePayload;
import com.example.demo.register.payload.studyRegisterMember.StudyRegisterMemberInsertPayload;
import com.example.demo.register.payload.studyRegisterMember.StudyRegisterMemberUpdatePayload;
import com.example.demo.register.service.DayService;
import com.example.demo.register.service.EnrollRegisterService;
import com.example.demo.register.service.StudyRegisterMemberService;
import com.example.demo.register.service.StudyRegisterService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.university.exception.CreditOfTermException;
import com.example.demo.university.exception.TermException;
import com.example.demo.university.model.CreditOfTerm;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.Term;
import com.example.demo.university.service.CreditOfTermService;
import com.example.demo.university.service.TermService;

import lombok.Setter;

@Service
@Setter
public class StudyRegisterBusiness {
//	@Autowired
//	UniversityService universityService;
//	
//	@Autowired
//	StudyRegisterService studyRegisterService;
//	
//	@Autowired
//	StudyRegisterTypeService studyRegisterTypeService;
	
	@Autowired
	JWTAuth jwtAuth;
	
//	@Autowired
//	CourseGroupDetService courseGroupDetService;
	
	@Autowired
	StudyProgramDetService studyProgramDetService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	TermService termService;
	
	@Autowired
	StudyRegisterService studyRegisterService;
	
	@Autowired
	StudyRegisterMemberService studyRegisterMemberService;
	
	@Autowired
	DayService dayService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	StudyProgramService studyProgramService;
	
	@Autowired
	CreditOfTermService creditOfTermService;
	
	@Autowired
	EnrollRegisterService enrollRegisterService;
	
	@Autowired
	BillProgramService billProgramService;
	
	@Autowired
	LogService logService;
	
	public List<StudyRegisterMemberAllJson> viewAllStudyRegisterMember (String studyRegisterUid) throws StudyRegisterException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(studyRegisterUid);
		return StudyRegisterMemberAllJson.studyRegisterCourseAllJsons(studyRegisterMemberService.studyRegisterMembers(studyRegister));
	}
	
	public List<StudyRegisterAllJson> viewAllStudyRegister (String termUid) throws TermException{
		Term term = termService.findTermByUuid(termUid);
		List<StudyRegister> studyRegisters = studyRegisterService.studyRegisterTerm(term);
		List<List<StudyRegisterMemberAllJson>> studyRegisterMemberAllListJsons = new ArrayList<>();
		List<StudyRegisterMemberAllJson> studyRegisterMemberAllJsons = new ArrayList<>();
		for (StudyRegister studyRegister : studyRegisters) {
			studyRegisterMemberAllJsons = StudyRegisterMemberAllJson.studyRegisterCourseAllJsons(studyRegisterMemberService.studyRegisterMembers(studyRegister));
			studyRegisterMemberAllListJsons.add(studyRegisterMemberAllJsons);
		}
		
		return StudyRegisterAllJson.studyRegisterCourseAllJsons(studyRegisters,studyRegisterMemberAllListJsons);
	}
	
	public List<StudyRegisterAllJson> viewAllStudyRegisterFac (String termUid) throws TermException, MemberException{
		Term term = termService.findTermByUuid(termUid);
		Faculty faculty = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid()).getFaculty();
		List<StudyRegister> studyRegisters = studyRegisterService.studyRegisterTermFac(term,faculty);
		List<List<StudyRegisterMemberAllJson>> studyRegisterMemberAllListJsons = new ArrayList<>();
		List<StudyRegisterMemberAllJson> studyRegisterMemberAllJsons = new ArrayList<>();
		for (StudyRegister studyRegister : studyRegisters) {
			studyRegisterMemberAllJsons = StudyRegisterMemberAllJson.studyRegisterCourseAllJsons(studyRegisterMemberService.studyRegisterMembers(studyRegister));
			studyRegisterMemberAllListJsons.add(studyRegisterMemberAllJsons);
		}
		return StudyRegisterAllJson.studyRegisterCourseAllJsons(studyRegisters, studyRegisterMemberAllListJsons);
//		return StudyRegisterAllJson.studyRegisterCourseAllJsons(studyRegisterService.studyRegisterTermFac(term,faculty));
	}
	
	public List<StudyRegisterTeacherJson> viewStudyRegisterTeacher (String termUid) throws TermException, MemberException{
		Member member = memberService.findMemberByUuid( jwtAuth.getCurrentMember().getMemberUuid());
		Term term = termService.findTermByUuid(termUid);
		return StudyRegisterTeacherJson.studyRegisterTeacherJsons(studyRegisterMemberService.studyRegisterTermMember(term,member));
	}
	
	public StudyRegisterAllJson viewStudyRegister (String uid) throws StudyRegisterException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(uid);
		List<StudyRegisterMemberAllJson> studyRegisterMemberAllJsons = new ArrayList<>();
		
		studyRegisterMemberAllJsons = StudyRegisterMemberAllJson.studyRegisterCourseAllJsons(studyRegisterMemberService.studyRegisterMembers(studyRegister));
		return StudyRegisterAllJson.packStudyRegisterPersonalAllJson(studyRegister, studyRegisterMemberAllJsons);
//		List<StudyRegisterMemberAllJson> = new StudyRegisterMemberAllJson.studyRegisterCourseAllJsons(studyRegisterMemberService.studyRegisterMembers(studyRegister));
//		return StudyRegisterAllJson.packStudyRegisterPersonalAllJson(studyRegisterService.findStudyRegisterUuid(Uid));
	}
	
	public CountCreditAllJson viewAllCountCredit (String Uid) throws MemberException, StudyProgramException, CreditOfTermException, TermException {
		Term term = termService.findTermByUuid(Uid);
		Member member = jwtAuth.getCurrentMember();
		Program program = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUserUuid()).getProgram();
		DegreeLevel degreeLevel = program.getDegreeLevel();
		StudyProgram studyProgram = studyProgramService.findProgram(program);
		CreditOfTerm creditOfTerm = creditOfTermService.findCreditOfTermProgram(program);
		
		int creditEnroll = 0;
		int creditEnrollTerm = 0;
		
		List<Subject> subjects = subjectService.subjectDegreeLevels(degreeLevel);
		
		Map<String, List<String>> maps = enrollRegisterService.enrollRegisterMemberStatus(member)
				.stream().map(r -> r.getStudyRegister().getSubject().getSubjectCode()).collect(Collectors.groupingBy(Function.identity()));
		for (Entry<String, List<String>> map  : maps.entrySet()) {
			for (Subject subject : subjects) {
				if(map.getKey().equals(subject.getSubjectCode())) {
					creditEnrollTerm = creditEnrollTerm + subject.getCredit();
				}
			}
		}

		Map<String, List<String>> mapterms = enrollRegisterService.enrollRegistersTable(member,term)
				.stream().map(r -> r.getStudyRegister().getSubject().getSubjectCode()).collect(Collectors.groupingBy(Function.identity()));
		for (Entry<String, List<String>> map  : mapterms.entrySet()) {
			for (Subject subject : subjects) {
				if(map.getKey().equals(subject.getSubjectCode())) {
					creditEnroll = creditEnroll + subject.getCredit();
				}
			}
		}
		
		int courseStructureCredits = studyProgram.getCourseStructure().getCourseStructureGeCredits() + studyProgram.getCourseStructure().getCourseStructureMajorCredits();
		int courseStructureCreditsMajor = studyProgram.getCourseStructure().getCourseStructureMajorCredits();
		int courseStructureCreditsGe = studyProgram.getCourseStructure().getCourseStructureGeCredits();
		int creditMax = creditOfTerm.getCreditMax();
		int creditMin = creditOfTerm.getCreditMin();
		
		return CountCreditAllJson.packCountCreditPersonalAllJson(courseStructureCredits,creditMax,creditMin,courseStructureCreditsMajor,courseStructureCreditsGe,creditEnroll,creditEnrollTerm);
	}
	

	public List<StudyRegisterStudentJson> viewStudyRegisterStudent (String termUid) throws TermException, MemberException{
		Member member = memberService.findMemberByUuid( jwtAuth.getCurrentMember().getMemberUuid());
		Term term = termService.findTermByUuid(termUid);
		return StudyRegisterStudentJson.studyRegisterStudentJsons(enrollRegisterService.enrollRegisters(member,term));
	}
	
	public List<StudyRegisterStudentJson> viewStudyRegisterStudentTable (String termUid) throws TermException, MemberException{
		Member member = memberService.findMemberByUuid( jwtAuth.getCurrentMember().getMemberUuid());
		Term term = termService.findTermByUuid(termUid);
		return StudyRegisterStudentJson.studyRegisterStudentJsons(enrollRegisterService.enrollRegistersTable(member,term));
	}
	
	public void saveEnrollRegister(String uid) throws EnrollRegisterException, StudyRegisterException, LogException {
		EnrollRegister enrollRegister = enrollRegisterService.findEnrollRegister(uid);
		if(enrollRegister.getEnrollRegisterStatus() == 0) {
			enrollRegister.setEnrollRegisterStatus(1);
			StudyRegister studyRegister = enrollRegister.getStudyRegister();
			if(studyRegister.getCapacityTotal() == 0) {
				throw StudyRegisterException.capacitySubjectTotalOver(studyRegister.getSubject().getSubjectCode());
			}
			studyRegister.setCapacityTotal(studyRegister.getCapacityTotal() - 1);
			studyRegisterService.saveStudyRegister(studyRegister);
			
			enrollRegisterService.saveEnrollRegister(enrollRegister);
			
			Log log =  new Log();
			log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
			log.setLogEvent("บันทึกข้อมูลการลงทะเบียน");
			logService.saveLog(log);
		}
		
	}
	
	public void delEnrollRegister(String uid) throws EnrollRegisterException, StudyRegisterException, LogException {
		EnrollRegister enrollRegister = enrollRegisterService.findEnrollRegister(uid);
		StudyRegister studyRegister = enrollRegister.getStudyRegister();
		studyRegister.setCapacityTotal(studyRegister.getCapacityTotal() + 1);
		studyRegisterService.saveStudyRegister(studyRegister);
		
		enrollRegisterService.delEnrollRegister(enrollRegister);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลการลงทะเบียน");
		logService.saveLog(log);
	}
	
	public void insertEnrollRegister(EnrollRegisterInsertPayload body) throws StudyRegisterException, MemberException, EnrollRegisterException, BillProgramException, LogException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(body.getStudyRegisterUid());
		
		Member member = jwtAuth.getCurrentMember();
		Term term = studyRegister.getTerm();
		
		if(enrollRegisterService.booleanEnrollRegister(member, term)) {
			String enrollSubCode = null;
			String studyRegisterieSubCode = studyRegister.getSubject().getSubjectCode();
			
			Day day = studyRegister.getDay();
			LocalTime timeBegin = studyRegister.getTimeBegin();
			LocalTime timeEnd = studyRegister.getTimeEnd();
			
			boolean memberTimeBegin = enrollRegisterService.booleanEnrollRegisterTimeBegin(member, term, day, timeBegin, timeEnd);
			boolean memberTimeEnd = enrollRegisterService.booleanEnrollRegisterTimeEnd(member, term, day, timeBegin, timeEnd);
			
			if(memberTimeBegin || memberTimeEnd) {
				throw EnrollRegisterException.enrollRegisterSubjectTimeUsed();
			}
			
//			boolean teacherTimeBegin = studyRegisterService.booleanMemberDateTimeBeginUsed(term, member, day, timeBegin, timeEnd);
//			boolean teacherTimeEnd = studyRegisterService.booleanMemberDateTimeEndUsed(term, member, day, timeBegin, timeEnd);
			
			List<EnrollRegister> enrollRegisters = enrollRegisterService.enrollRegisters(member, term);
			for (EnrollRegister enrollRegister : enrollRegisters) {
				enrollSubCode = enrollRegister.getStudyRegister().getSubject().getSubjectCode();
				if(enrollSubCode.equals(studyRegisterieSubCode)) {
					throw  EnrollRegisterException.enrollRegisterSubjectUsed();
				}
			}
			
		}
		
		if(studyRegister.getCapacityTotal() == 0) {
			throw StudyRegisterException.capacityTotalOver();
		}
//		studyRegister.setCapacityTotal(studyRegister.getCapacityTotal() - 1);
		
		Program program = memberService.findMemberFacultyMemberByUuid(member.getMemberUuid()).getProgram();
		BillProgram billProgram = billProgramService.findProgram(program);
		
		EnrollRegister enrollRegister = new EnrollRegister();
		enrollRegister.setMember(member);
		enrollRegister.setStudyRegister(studyRegister);
		enrollRegister.setBillProgram(billProgram);
		enrollRegisterService.saveEnrollRegister(enrollRegister);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลวิชาการลงทะเบียน");
		logService.saveLog(log);
	}
	
//	public List<StudyRegisterStudyRegisterMemberJson> viewStudyRegisterStudyRegisterMember (String dayUid,String memberUid) throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException, TermException{
//		Term term = termService.findTermByUuid(dayUid);
//		Member member = memberService.findMemberByUuid(memberUid);
//		
//		List<StudyRegister> studyRegister = studyRegisterService.StudyRegisterTermMember(term, member);
//		
////		List<CourseGroupDet> courseGroupDets = new ArrayList<>();
//		
//		CourseGroupDet courseGroupDet = null;
//		
//		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
//		HashMap<String, Object> subData = null;
//		
//		for (StudyRegister studyRegisterLoop : studyRegister) {
//			
//			
//			if(studyRegisterLoop.getType().equals("FAC")){
//				courseGroupDet = courseGroupDetService.findByCourseGroupDetId(studyRegisterLoop.getSubjectId());
//				subData = new HashMap<String, Object>();
//				subData.put("subject_id", studyRegisterLoop.getSubjectId());
//				subData.put("name", courseGroupDet.getName());
//				subData.put("sec", studyRegisterLoop.getSec());
//				subData.put("type", studyRegisterLoop.getType());
//				subData.put("capacity_max", studyRegisterLoop.getCapacityMax());
//				subData.put("capacity_total", studyRegisterLoop.getCapacityTotal());
//				subData.put("member_name", studyRegisterLoop.getMember().getMemberNameTh());
//				subData.put("day_name", studyRegisterLoop.getDay().getDayNameTh());
//				subData.put("begin", studyRegisterLoop.getTimeBegin());
//				subData.put("end", studyRegisterLoop.getTimeEnd());
//				subData.put("room_name", studyRegisterLoop.getRoom().getRoomName());
//				subData.put("building_name", studyRegisterLoop.getRoom().getBuilding().getBuildingName());
//				
//				data.add(subData);
//				
//				
//				
//				
//			}else {
//				
//			}
//			
//			
//			
//		}
//		
//		
//		ArrayList<HashMap<String, HashMap<String, Object>>> dayGroupMain = new ArrayList<HashMap<String, HashMap<String, Object>>>();
//		HashMap<String, HashMap<String, Object>> dayGroup = null;
//		
//		
//		for(int i = 0; i < data.size(); i++) {
//			
//			dayGroup = new HashMap<String, HashMap<String, Object>>();
//			
//			if(data.get(i).get("day_name").equals("อาทิตย์")) {
//				
//				dayGroup.put("sunday", data.get(i));
//			} else if(data.get(i).get("day_name").equals("จันทร์")) {
//				
//				dayGroup.put("monday", data.get(i));
//			} else if(data.get(i).get("day_name").equals("อังคาร")) {
//				
//				dayGroup.put("tuesday", data.get(i));
//			}else if(data.get(i).get("day_name").equals("พุธ")) {
//				
//				dayGroup.put("wednesday", data.get(i));
//			} else if(data.get(i).get("day_name").equals("พฤหัสบดี")) {
//				
//				dayGroup.put("thursday", data.get(i));
//			} else if(data.get(i).get("day_name").equals("ศุกร์")) {
//				
//				dayGroup.put("friday", data.get(i));
//			} else if(data.get(i).get("day_name").equals("เสาร์")) {
//				
//				dayGroup.put("saturay", data.get(i));
//				
//			}
//			
//			dayGroupMain.add(dayGroup);
//			
//		}
//		
//		
//		return StudyRegisterStudyRegisterMemberJson.studyRegisterStudyRegisterMemberJsons(dayGroupMain);
//	}
//	
//	
//	public List<List<StudyRegisterStudyRegisterMemberJson>> viewAllStudyRegisterStudyRegisterMember (String dayUid) throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException, TermException{
//		Term term = termService.findTermByUuid(dayUid);
//		
//		Map<Long, Long> studyRegister = studyRegisterService.StudyRegisterTerm(term)
//				.stream().map(r -> r.getMember().getMemberId()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//		
//		String[] teacher_id = {};
//		
//		String test = studyRegister.keySet().toString();
//		
//		
//		test = test.substring(1, test.length()-1);
//		
//		teacher_id = test.split(", ");
//		
//		
//		ArrayList<List<StudyRegister>> studyRegisterMainest = new ArrayList<List<StudyRegister>>();
//		List<StudyRegister> studyRegisterMain = null;
//		Member member = new Member();
//		
//		for(int i = 0; i < teacher_id.length; i++) {
//			
//			member = memberService.findMemberId(Long.parseLong(teacher_id[i]) );
//			studyRegisterMain = studyRegisterService.StudyRegisterTermMember(term, member);
//			
//			studyRegisterMainest.add(studyRegisterMain);
//			
//		}
//		
//		ArrayList<ArrayList<HashMap<String, Object>>> dataMain = new ArrayList<ArrayList<HashMap<String, Object>>>();
//		ArrayList<HashMap<String, Object>> data = null;
//		HashMap<String, Object> subData = null;
//		CourseGroupDet courseGroupDet = null;
//		
//		int size = 0;
//		
//		for(int i = 0; i < studyRegisterMainest.size(); i++) {
//			
//			size = studyRegisterMainest.get(i).size();
//			data = new ArrayList<HashMap<String, Object>>();
//			
//			for(int j = 0; j < size; j++) {
//				
//				courseGroupDet = courseGroupDetService.findByCourseGroupDetId(studyRegisterMainest.get(i).get(j).getSubjectId());
//				
//				subData = new HashMap<String, Object>();
//				
//				subData.put("subject_id", studyRegisterMainest.get(i).get(j).getSubjectId());
//				subData.put("name", courseGroupDet.getName());
//				subData.put("sec", studyRegisterMainest.get(i).get(j).getSec());
//				subData.put("type", studyRegisterMainest.get(i).get(j).getType());
//				subData.put("capacity_max", studyRegisterMainest.get(i).get(j).getCapacityMax());
//				subData.put("capacity_total", studyRegisterMainest.get(i).get(j).getCapacityTotal());
//				subData.put("member_name", studyRegisterMainest.get(i).get(j).getMember().getMemberNameTh());
//				subData.put("day_name", studyRegisterMainest.get(i).get(j).getDay().getDayNameTh());
//				subData.put("begin", studyRegisterMainest.get(i).get(j).getTimeBegin());
//				subData.put("end", studyRegisterMainest.get(i).get(j).getTimeEnd());
//				subData.put("room_name", studyRegisterMainest.get(i).get(j).getRoom().getRoomName());
//				subData.put("building_name", studyRegisterMainest.get(i).get(j).getRoom().getBuilding());
//				
//				data.add(subData);
//			}
//			dataMain.add(data);
//			
//		}
//		
//		
//		return StudyRegisterStudyRegisterMemberJson.studyRegisterStudyRegisterMemberJsonsSuper(dataMain);
//	}
	
//	public List<StudyRegister> viewStudyRegisterStudyRegisterMember () throws CourseGroupDetException, GeSubjectGroupDetException, DayException, MemberException{
//		
//		
//		Day day = dayService.findDayByUuid("1da3d66f-496b-11ed-9dec-fc349794197a");
//		Member member = memberService.findMemberByUuid("2b14600c-13c3-4466-88ef-ec5fe023e44d");
//		
//		List<StudyRegister> studyRegister = studyRegisterService.StudyRegisterDayMember(day, member);
//		
//		return studyRegister;
//		
//		
//	}	
	
	
	public List<DayAllJson> viewAllDay (){
		return DayAllJson.dayCourseAllJsons(dayService.days());
	}
	
	
	
//	
	public StudyRegisterUidJson insertStudyRegister(StudyRegisterInsertPayload body) throws SubjectException, MemberException, TermException, RoomException, DayException, StudyRegisterException, StudyProgramDetException, LogException {
		String roleName = jwtAuth.getCurrentUser().getRole().getRoleName();
		Subject subject = subjectService.findSubjectByUuid(body.getSubjectUid());
		String subjectCode = subject.getSubjectCode();
//		Member member = memberService.findMemberByUuid(body.getMemberUid());
		String type = null;
		String sec = null;
		Term term = termService.findTermByUuid(body.getTermUid());
		
		DegreeLevel degreeLevel = term.getDegreeLevel();
		
		Room room = roomService.findRoomByUuid(body.getRoomUid());
		String capacityMax = body.getCapacityMax();
		Day day = dayService.findDayByUuid(body.getDayUid());
		LocalTime timeBegin = body.getTimeBegin();
		LocalTime timeEnd = body.getTimeEnd();
		
		if(capacityMax == null || capacityMax.equals("")) {
			throw StudyRegisterException.capacityMaxEmpty();
		}
		
		if(timeBegin.isAfter(timeEnd) || timeEnd.isBefore(timeBegin)) {
			throw StudyRegisterException.timeIncorrect();
		}
		
		boolean roomTimeBegin = studyRegisterService.booleanRoomDateTimeBeginUsed(term, room, day, timeBegin, timeEnd);
		boolean roomTimeEnd = studyRegisterService.booleanRoomDateTimeEndUsed(term, room, day, timeBegin, timeEnd);
		
		if(roomTimeBegin || roomTimeEnd) {
			throw StudyRegisterException.timeUsed();
		}
		
		if(Integer.parseInt(capacityMax) > room.getRoomCapacity()) {
			throw StudyRegisterException.capacityIncorrect();
		}
		
//		boolean teacherTimeBegin = studyRegisterService.booleanMemberDateTimeBeginUsed(term, member, day, timeBegin, timeEnd);
//		boolean teacherTimeEnd = studyRegisterService.booleanMemberDateTimeEndUsed(term, member, day, timeBegin, timeEnd);
		
//		if(teacherTimeBegin || teacherTimeEnd) {
//			throw StudyRegisterException.teacherTimeUsed();
//		}
			
		if(roleName.equals("ROLE_ACADEMIC_FAC")) { 
			
			MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUserUuid());
			Faculty facultyMember = memberFaculty.getFaculty();
			type = "FAC";
			if(!studyProgramDetService.booleanSubjectCode(subjectCode, facultyMember, degreeLevel)) {
				throw StudyRegisterException.subjectStudyProgramDetNotFound();
			}
		}else {
			type = "GE";
		}
		
		if(!studyRegisterService.booleanSubject(term,subject, type)) {
			sec = Integer.toString(1);
		}else {
			StudyRegister studyRegister = studyRegisterService.findMaxSecSubject(term,subject, type);
			sec = Integer.toString(studyRegister.getSec() + 1) ;
		}
		
		StudyRegister studyRegister = new StudyRegister();
		studyRegister.setSubject(subject);
		studyRegister.setRoom(room);
//		studyRegister.setMember(member);
		studyRegister.setCapacityMax(Integer.parseInt(capacityMax));
		studyRegister.setCapacityTotal(Integer.parseInt(capacityMax));
		studyRegister.setType(type);
		studyRegister.setDay(day);
		studyRegister.setSec(Integer.parseInt(sec));
		studyRegister.setTimeBegin(timeBegin);
		studyRegister.setTimeEnd(timeEnd);
		studyRegister.setTerm(term);
		
		studyRegisterService.saveStudyRegister(studyRegister);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลเปิดกลุ่มการลงทะเบียน");
		logService.saveLog(log);
		
		String uid = studyRegister.getStudyRegisterUuid();
		
//		String uid = "kap";
		
		return StudyRegisterUidJson.packStudyRegisterUidJson(uid);
	}
	
	public void insertStudyRegisterMember(StudyRegisterMemberInsertPayload body) throws StudyRegisterException, MemberException, StudyRegisterMemberException, LogException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(body.getStudyRegisterUid());
		Member member = memberService.findMemberByUuid(body.getMemberUid());
		Term term = studyRegister.getTerm();
		Day day = studyRegister.getDay();
		LocalTime timeBegin = studyRegister.getTimeBegin();
		LocalTime timeEnd = studyRegister.getTimeEnd();
		
//		Boolean checkError = null;
		
		if(studyRegisterMemberService.booleanMemberUsed(studyRegister, member)){
			throw StudyRegisterMemberException.studyRegisterMemberUsed(member.getMemberNameTh());
			
		}
		boolean teacherTimeBegin = studyRegisterMemberService.booleanMemberDateTimeBeginUsed(term, member, day, timeBegin, timeEnd);
		boolean teacherTimeEnd = studyRegisterMemberService.booleanMemberDateTimeEndUsed(term, member, day, timeBegin, timeEnd);
		
		if(teacherTimeBegin || teacherTimeEnd) {
//			if(checkError) {
//				studyRegisterService.delStudyRegister(studyRegister);
//			}
			throw StudyRegisterMemberException.teacherTimeUsed(member.getMemberNameTh());
		}
		
		StudyRegisterMember studyRegisterMember = new StudyRegisterMember();
		studyRegisterMember.setStudyRegister(studyRegister);
		studyRegisterMember.setMember(member);
		studyRegisterMemberService.saveStudyRegisterMember(studyRegisterMember);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มอาจารย์ผู้สอน");
		logService.saveLog(log);
	}
	
	public void updateStudyRegisterMember(StudyRegisterMemberUpdatePayload body) throws StudyRegisterException, MemberException, StudyRegisterMemberException, LogException {
		StudyRegisterMember studyRegisterMember = studyRegisterMemberService.findStudyRegisterMemberUuid(body.getStudyRegisterMemberUid());
		StudyRegister studyRegister = studyRegisterMember.getStudyRegister();
		Member member = memberService.findMemberByUuid(body.getMemberUid());
		Member memberOld = studyRegisterMember.getMember();
		Term term = studyRegister.getTerm();
		Day day = studyRegister.getDay();
		LocalTime timeBegin = studyRegister.getTimeBegin();
		LocalTime timeEnd = studyRegister.getTimeEnd();
		
		if(!member.equals(memberOld)){
			if(studyRegisterMemberService.booleanMemberUsed(studyRegister, member)){
				throw StudyRegisterMemberException.studyRegisterMemberUsed();
			}
			boolean teacherTimeBegin = studyRegisterMemberService.booleanMemberDateTimeBeginUsed(term, member, day, timeBegin, timeEnd);
			boolean teacherTimeEnd = studyRegisterMemberService.booleanMemberDateTimeEndUsed(term, member, day, timeBegin, timeEnd);
			
			if(teacherTimeBegin || teacherTimeEnd) {
				throw StudyRegisterException.teacherTimeUsed();
			}
		}
		
		studyRegisterMember.setStudyRegister(studyRegister);
		studyRegisterMember.setMember(member);
		studyRegisterMemberService.saveStudyRegisterMember(studyRegisterMember);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขอาจารย์ผู้สอน");
		logService.saveLog(log);
	}
	
	public void delStudyRegisterMember(String uid) throws StudyRegisterMemberException, LogException {
		StudyRegisterMember studyRegisterMember = studyRegisterMemberService.findStudyRegisterMemberUuid(uid);
		studyRegisterMemberService.delStudyRegisterMember(studyRegisterMember);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลอาจารย์ผู้สอน");
		logService.saveLog(log);
	}
	
	
	
	public void updateStudyRegister(StudyRegisterUpdatePayload body) throws StudyRegisterException, SubjectException, MemberException, RoomException, DayException, StudyProgramDetException, LogException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(body.getStudyRegisterUid());
		String roleName = jwtAuth.getCurrentUser().getRole().getRoleName();
		Subject subject = subjectService.findSubjectByUuid(body.getSubjectUid());
		Subject subjectOld = studyRegister.getSubject();
		String subjectCode = subject.getSubjectCode();
//		Member member = memberService.findMemberByUuid(body.getMemberUid());
//		Member memberOld = studyRegister.getMember();
		String type = null;
		String sec =  Integer.toString(studyRegister.getSec());
		Term term = studyRegister.getTerm();
		DegreeLevel degreeLevel = term.getDegreeLevel();
		Room room = roomService.findRoomByUuid(body.getRoomUid());
		Room roomOld = studyRegister.getRoom();
		String capacityMax = body.getCapacityMax();
		String capacityMaxOld = Integer.toString(studyRegister.getCapacityMax());
		Day day = dayService.findDayByUuid(body.getDayUid());
		Day dayOld = studyRegister.getDay();
		LocalTime timeBegin = body.getTimeBegin();
		LocalTime timeEnd = body.getTimeEnd();
		LocalTime timeBeginOld = studyRegister.getTimeBegin();
		LocalTime timeEndOld = studyRegister.getTimeEnd();
		
			
		if(!timeBegin.equals(timeBeginOld) || !timeEnd.equals(timeEndOld) || !day.equals(dayOld) ){
			if(timeBegin.isAfter(timeEnd) || timeEnd.isBefore(timeBegin)) {
				throw StudyRegisterException.timeIncorrect();
			}
			boolean roomTimeBegin = studyRegisterService.booleanRoomDateTimeBeginUsed(term, room, day, timeBegin, timeEnd);
			boolean roomTimeEnd = studyRegisterService.booleanRoomDateTimeEndUsed(term, room, day, timeBegin, timeEnd);
			
			if(roomTimeBegin || roomTimeEnd) {
				throw StudyRegisterException.timeUsed();
			}
		}
		
		if(!room.equals(roomOld)){
			boolean roomTimeBegin = studyRegisterService.booleanRoomDateTimeBeginUsed(term, room, day, timeBegin, timeEnd);
			boolean roomTimeEnd = studyRegisterService.booleanRoomDateTimeEndUsed(term, room, day, timeBegin, timeEnd);
			
			if(roomTimeBegin || roomTimeEnd) {
				throw StudyRegisterException.timeUsed();
			}
		}
		
		if(!capacityMax.equals(capacityMaxOld)){
			if(capacityMax == null || capacityMax.equals("")) {
				throw StudyRegisterException.capacityMaxEmpty();
			}
			if(Integer.parseInt(capacityMax) > room.getRoomCapacity()) {
				throw StudyRegisterException.capacityIncorrect();
			}
		}
		
//		if(!member.equals(memberOld)){
//			boolean teacherTimeBegin = studyRegisterService.booleanMemberDateTimeBeginUsed(term, member, day, timeBegin, timeEnd);
//			boolean teacherTimeEnd = studyRegisterService.booleanMemberDateTimeEndUsed(term, member, day, timeBegin, timeEnd);
//			
//			if(teacherTimeBegin || teacherTimeEnd) {
//				throw StudyRegisterException.teacherTimeUsed();
//			}
//		}
				
		if(!subject.equals(subjectOld)) {
			if(roleName.equals("ROLE_ACADEMIC_FAC")) { 
				type = "FAC";
				MemberFaculty memberFaculty = memberService.findMemberFacultyMemberByUuid(jwtAuth.getCurrentUserUuid());
				Faculty facultyMember = memberFaculty.getFaculty();
				type = "FAC";
				if(!studyProgramDetService.booleanSubjectCode(subjectCode, facultyMember, degreeLevel)) {
					throw StudyRegisterException.subjectStudyProgramDetNotFound();
				}
			}else {
				type = "GE";
			}
			if(!studyRegisterService.booleanSubject(term,subject, type)) {
				sec = Integer.toString(1);
			}else {
				StudyRegister studyRegisterOld = studyRegisterService.findMaxSecSubject(term,subject, type);
				sec = Integer.toString(studyRegisterOld.getSec() + 1) ;
			}
		}
		
	
		studyRegister.setSubject(subject);
		studyRegister.setRoom(room);
//		studyRegister.setMember(member);
		studyRegister.setCapacityMax(Integer.parseInt(capacityMax));
		studyRegister.setCapacityTotal(Integer.parseInt(capacityMax));
		studyRegister.setType(type);
		studyRegister.setDay(day);
		studyRegister.setSec(Integer.parseInt(sec));
		studyRegister.setTimeBegin(timeBegin);
		studyRegister.setTimeEnd(timeEnd);
		studyRegister.setTerm(term);
		
		studyRegisterService.saveStudyRegister(studyRegister);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลเปิดกลุ่มการลงทะเบียน");
		logService.saveLog(log);
		
	}
	
	public void delStudyRegister(String uid) throws StudyRegisterException, LogException {
		StudyRegister studyRegister = studyRegisterService.findStudyRegisterUuid(uid);
		studyRegisterService.delStudyRegister(studyRegister);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลเปิดกลุ่มการลงทะเบียน");
		logService.saveLog(log);
	}
	
//	public List<StudyRegisterJson> viewAllStudyRegister() {
//		return StudyRegisterJson.packStudyRegisterJson(studyRegisterService.getAllStudyRegister(jwtAuth.getCurrentUser().getUniversity()));
//	}
//	
//	public StudyRegisterJson viewStudyRegister(String uid) throws StudyRegisterException  {
//		return StudyRegisterJson.packStudyRegisterJson(studyRegisterService.findStudyRegisterByUuid(uid));
//	}
	
}
