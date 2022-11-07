package com.example.demo.subject.business;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.model.Address;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.service.AddressService;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.member.model.Role;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.subject.exception.CourseStructureDetException;
import com.example.demo.subject.exception.GroupSubjectException;
import com.example.demo.subject.exception.SubjectException;
import com.example.demo.subject.json.SubjectAllJson;
import com.example.demo.subject.model.CourseStructure;
import com.example.demo.subject.model.CourseStructureDet;
import com.example.demo.subject.model.GroupSubject;
import com.example.demo.subject.model.Subject;
import com.example.demo.subject.payload.subject.StudentUploadPayload;
import com.example.demo.subject.payload.subject.SubjectInsertPayload;
import com.example.demo.subject.payload.subject.SubjectUpdatePayload;
import com.example.demo.subject.payload.subject.SubjectUploadPayload;
import com.example.demo.subject.service.CourseStructureDetService;
import com.example.demo.subject.service.CourseStructureService;
import com.example.demo.subject.service.GroupSubjectService;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.subject.service.TypeSubjectService;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;
import com.example.demo.university.service.DegreeLevelService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import lombok.Setter;

@Service
@Setter
public class SubjectBusiness {
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	GroupSubjectService groupSubjectService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	TypeSubjectService typeSubjectService;
	
	@Autowired
	CourseStructureService courseStructureService;
	
	@Autowired
	CourseStructureDetService courseStructureDetService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void uploadStudent(StudentUploadPayload body) throws IOException, AddressException, FacultyException, ProgramException, NumberFormatException, MemberException {
		Tambon tambon = addressService.getTambon("28595e97-f847-11ec-a534-04421ad6981f");
		String addressDetailTh = "112/8 หมู่ 8";
		String addressDetailEn = "112/8 Mu. 8";
		University university = jwtAuth.getCurrentUser().getUniversity();
		LocalDate memberBirthday = LocalDate.parse("2001-12-08");
		String tel = "0624989078";
		
		Faculty faculty = facultyService.findFacultyByUuid("1e04a00d-3218-4493-a0ea-8a594b1f3e2c");
		Program program = programService.findProgramByUuid("50d09a89-d16c-4551-9182-61d1c76e0013");
		
		String memberPassword = "1";
		
		
		InputStream inputStream = body.getFile().getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream,"UTF-8");
		for (Record record : parseAllRecords) {
			String studentNumber = null;
			
			String year = record.getString("ชั้นปี");
			System.out.println(year);
			String createTimeStr = null;
			LocalDate date =null;
			
			if(year.equals("1")) {
				 
				createTimeStr="2022-11-06";
				date = LocalDate.parse(createTimeStr);
				year = "65";
			}if(year.equals("2")) {
				createTimeStr="2021-11-06";
				date = LocalDate.parse(createTimeStr);
				year = "64";
			}if(year.equals("3")) {
				createTimeStr="2020-11-06";
				date = LocalDate.parse(createTimeStr);
				year = "63";
			}if(year.equals("4")) {
				createTimeStr="2019-11-06";
				date = LocalDate.parse(createTimeStr);
				year = "62";
			}
			System.out.println(year);
			
			LocalDateTime createTime = date.atStartOfDay();
			System.out.println(createTime);
			String universityNumber = university.getUniversityNumber();
			String facNumber = faculty.getFacultyNumber();
			String degreeNumber = program.getDegreeLevel().getDegreeLevelNumber();
			
			if(memberService.studentNumberBoolean(faculty)) {
				int studentMaxNum = Integer.parseInt(memberService.findFirstFacultyNumberDesc(faculty).getStudentNumber());
				studentNumber = Integer.toString(studentMaxNum + 1);
			}else {
				studentNumber = "0";
			}
			
			if (Integer.parseInt(studentNumber) > 9999) {
				throw MemberException.studentOver();
			}
			
			if (studentNumber.length() < 2) {
				studentNumber = "000" +studentNumber;
			}else if (studentNumber.length() < 3) {
				studentNumber = "00" +studentNumber;
			}else if (studentNumber.length() < 4) {
				studentNumber = "0" +studentNumber;
			}
			String sId = year + universityNumber + facNumber + degreeNumber + studentNumber;
			
			Address address = new Address();
			address.setAddressDetailTh(addressDetailTh);
			address.setAddressDetailEn(addressDetailEn);
			address.setTambon(tambon);
			addressService.saveAddress(address);
			
			Role role =  memberService.getRole("d17ea4a5-0445-11ed-8e7d-0492261420bd");
			
			Member member = new Member();
			member.setMemberUsername(record.getString("username"));
			member.setMemberPassword(passwordEncoder.encode(memberPassword));
			
			member.setMemberNameTh(record.getString("ชื่อไทย"));
			member.setMemberNameEn(record.getString("ชื่ออังกฤษ"));
			member.setMemberBirthday(memberBirthday);
			member.setMemberTel(tel);
			member.setMemberEmail(record.getString("email"));
			member.setMemberStatus(0);
			member.setAddress(address);
			member.setUniversity(university);
			member.setRole(role);
			member.setMemberImg("user.png");
			member.setCreatedAt(createTime);
			member.setUpdatedAt(createTime);
			memberService.saveMember(member);
			
			MemberFaculty memberFaculty = new MemberFaculty();
			memberFaculty.setFaculty(faculty);
			memberFaculty.setMember(member);
			memberFaculty.setProgram(program);
			memberFaculty.setStudentId(sId);
			memberFaculty.setStudentNumber(studentNumber);
			memberService.saveMemberFaculty(memberFaculty);
			
			address.setMember(member);
			addressService.saveAddress(address);
		}
	}
	
	public void uploadSubject(SubjectUploadPayload body) throws IOException, GroupSubjectException, SubjectException, LogException, CourseStructureDetException {
		GroupSubject groupSubject = groupSubjectService.findGroupSubjectByUuid(body.getGroupSubjectUid());
		InputStream inputStream = body.getFile().getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
//		parser.beginParsing(inputStream, "UTF-8")
		
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream,"UTF-8");
//		List<Subject> subjects = subjectService.subjectDegreeLevels(groupSubject.getDegreeLevel());
		for (Record record : parseAllRecords) {
			if(subjectService.booleanSubjectDegreeLevel(record.getString("รหัสวิชา"), groupSubject.getDegreeLevel())) {
				Subject subject = subjectService.findSubjectBySubjectCode(record.getString("รหัสวิชา"), groupSubject.getDegreeLevel());
				subject.setCredit(Integer.parseInt(record.getString("หน่วยกิต")));
				subject.setName(record.getString("ชื่อวิชา"));
				subjectService.saveSubject(subject);
			}else {
				Subject subjectNew = new Subject();
				subjectNew.setName(record.getString("ชื่อวิชา"));
				subjectNew.setCredit(Integer.parseInt(record.getString("หน่วยกิต")));
				subjectNew.setGroupSubject(groupSubject);
				subjectNew.setSubjectCode(record.getString("รหัสวิชา"));
				subjectService.saveSubject(subjectNew);
			}
		}
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มไฟล์ข้อมูลวิชา");
		logService.saveLog(log);
//		System.out.println("Upload CSV");
	}
	
	public List<SubjectAllJson> viewAllSubject (String groupSubjectUid) throws GroupSubjectException {
		GroupSubject groupSubject = groupSubjectService.findGroupSubjectByUuid(groupSubjectUid);
		return SubjectAllJson.subjectCourseAllJsons(subjectService.subjects(groupSubject));
	}
	
	public SubjectAllJson viewSubject (String uid) throws SubjectException{
		return SubjectAllJson.packSubjectPersonalAllJson(subjectService.findSubjectByUuid(uid));
	}
	
	public void insertSubject(SubjectInsertPayload body) throws GroupSubjectException, SubjectException, CourseStructureDetException, LogException {
		String subjectCode = body.getCode();
		String name = body.getName();
		String credit = body.getCredit();
		GroupSubject groupSubject = groupSubjectService.findGroupSubjectByUuid(body.getGroupSubjectUid());
		
		if(subjectCode == null || subjectCode.equals("")) {
			throw SubjectException.subjectCodeEmpty();
		}
		if(name == null || name.equals("")) {
			throw SubjectException.subjectNameEmpty();
		}
		if(credit == null || credit.equals("")) {
			throw SubjectException.subjectCreditEmpty();
		}
		if(subjectService.booleanName(name, groupSubject)) {
			throw SubjectException.subjectNameUsed();
		}
		if(subjectService.booleanSubjectDegreeLevel(subjectCode, groupSubject.getDegreeLevel())) {
			throw SubjectException.subjectCodeUsed();
		}
		
		Subject subject = new Subject();
		subject.setName(name);
		subject.setCredit(Integer.parseInt(credit));
		subject.setGroupSubject(groupSubject);
		subject.setSubjectCode(subjectCode);
		subjectService.saveSubject(subject);
		
		if(subject.getGroupSubject().getTypeSubject().getTypeSubjectNameEn().equals("GE")){
			DegreeLevel degreeLevel = subject.getGroupSubject().getDegreeLevel();
			if(courseStructureService.booleanCourseStructure(degreeLevel)) {
				List<CourseStructure> courseStructures = courseStructureService.courseStructureDegreeLevels(degreeLevel);
				for (CourseStructure courseStructure : courseStructures) {
					CourseStructureDet courseStructureDet = new CourseStructureDet();
					courseStructureDet.setCourseStructure(courseStructure);
					courseStructureDet.setSubject(subject);
					courseStructureDetService.saveCourseStructureDet(courseStructureDet);
				}
			}	
		}
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลวิชา");
		logService.saveLog(log);
	}
	
	public void updateSubject(SubjectUpdatePayload body)throws SubjectException, LogException {
		Subject subject = subjectService.findSubjectByUuid(body.getSubjectUid());
		long subId = subject.getSubjectId();
		String subjectCode = body.getCode();
		GroupSubject groupSubject = subject.getGroupSubject();
		String nameOld = subject.getName();
		String name = body.getName();
		String credit = body.getCredit();
		
		if(name == null || name.equals("")) {
			throw SubjectException.subjectNameEmpty();
		}
		if(credit == null || credit.equals("")) {
			throw SubjectException.subjectCreditEmpty();
		}
		
		if(!name.equals(nameOld)) {
			if(subjectService.booleanName(name, groupSubject)) {
				throw SubjectException.subjectNameUsed();
			}
		}
		
		if(subjectService.booleanSubjectDegreeLevelUsed(subjectCode, groupSubject.getDegreeLevel(), subId)) {
			throw SubjectException.subjectCodeUsed();
		}
		
		subject.setName(name);
		subject.setCredit(Integer.parseInt(credit));
		subject.setSubjectCode(subjectCode);
		subjectService.saveSubject(subject);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลวิชา");
		logService.saveLog(log);
	}
	
	public void delSubject(String uid)throws SubjectException, LogException {
		Subject subject = subjectService.findSubjectByUuid(uid);
		subjectService.delSubject(subject);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลวิชา");
		logService.saveLog(log);

	}
}
