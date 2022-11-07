package com.example.demo.university.business;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.service.AddressService;
import com.example.demo.faculty.exception.FacultyException;
import com.example.demo.faculty.exception.ProgramException;
import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;
import com.example.demo.faculty.service.FacultyService;
import com.example.demo.faculty.service.ProgramService;
import com.example.demo.file.constant.TypeImage;
import com.example.demo.file.service.FileStorageService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.json.UniversityJson;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;
import com.example.demo.university.payload.university.UniversityInsertPayload;
import com.example.demo.university.payload.university.UniversityUpdatePayload;
import com.example.demo.university.service.DegreeLevelService;
import com.example.demo.university.service.UniversityService;

import lombok.Setter;

@Service
@Setter
public class UniversityBusiness {
	@Autowired
	UniversityService universityService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	ProgramService programService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	LogService logService;
	
	public void insertUniversity(UniversityInsertPayload body) throws UniversityException, IOException, AddressException, MemberException, DegreeLevelException, FacultyException, ProgramException, LogException{
		String universityCodeName = body.getUniversityCodeName();
		String universityNameTh = body.getUniversityNameTh();
		String universityNameEn = body.getUniversityNameEn();
		String universityAddress = body.getUniversityAddress();
		Tambon tambon = addressService.getTambon(body.getTambonUuid());
		
		if(!jwtAuth.getCurrentMember().getUniversity().getUniversityUuid().equals("767e06ce-f848-11ec-a534-04421ad6981f")) {
			throw UniversityException.ForbiddenError();
		}
		
		if(universityNameTh == null || universityNameTh.equals("")) {
			throw UniversityException.universityNameThEmpty();
		}
		if(universityCodeName == null || universityCodeName.equals("")) {
			throw UniversityException.universityCodeNameEmpty();
		}
		if(universityNameEn == null || universityNameEn.equals("")) {
			throw UniversityException.universityNameEnEmpty();
		}
		if(universityAddress == null || universityAddress.equals("")) {
			throw UniversityException.universityAddressEmpty();
		}
		if(universityService.getUniversityNameTh(universityNameTh)) {
			throw UniversityException.universityNameThUsed();
		}
		if(universityService.getUniversityNameEn(universityNameEn)) {
			throw UniversityException.universityNameEnUsed();
		}
		if(universityService.getUniversityCodeName(universityCodeName)) {
			throw UniversityException.universityCodeNameUsed();
		}
		University universityMaxNumber = universityService.findFirstUniversityNumberDesc();
		
		int numUniversityMaxNumber = Integer.parseInt(universityMaxNumber.getUniversityNumber());
		
		String UniversityNumber = Integer.toString(numUniversityMaxNumber + 1);
		
		if (Integer.parseInt(UniversityNumber) > 99) {
			throw UniversityException.universityOver();
		}
		
		if (UniversityNumber.length() < 2) {
			UniversityNumber = 0+UniversityNumber;
		}
		
		University university = new University();
		university.setUniversityNumber(UniversityNumber);
		university.setUniversityNameTh(universityNameTh);
		university.setUniversityNameEn(universityNameEn);
		university.setUniversityAddress(universityAddress);
		university.setUniversityCodeName(universityCodeName);
		university.setTambon(tambon);
		
		
		
		if(body.getPic().getContentType() != null) {
			
			MultipartFile pic = body.getPic();
			String farmImageName = fileStorageService.storeImageFile(pic, TypeImage.UNIVERSITY);
			university.setUniversityImg(farmImageName);
		}else {
			university.setUniversityImg("university.png");
		}
		
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
		member.setUniversity(university);
		
		universityService.saveUniversity(university);
		
		DegreeLevel degreeLevel1 = new DegreeLevel();
		degreeLevel1.setName("ป.ตรี");
		degreeLevel1.setDegreeLevelNumber("1");
		degreeLevel1.setUniversity(university);
		degreeLevelService.saveDegreeLevel(degreeLevel1);
		
		DegreeLevel degreeLevel2 = new DegreeLevel();
		degreeLevel2.setName("ป.โท");
		degreeLevel1.setDegreeLevelNumber("2");
		degreeLevel2.setUniversity(university);
		degreeLevelService.saveDegreeLevel(degreeLevel2);
		
		DegreeLevel degreeLevel3 = new DegreeLevel();
		degreeLevel3.setName("ป.เอก");
		degreeLevel1.setDegreeLevelNumber("3");
		degreeLevel3.setUniversity(university);
		degreeLevelService.saveDegreeLevel(degreeLevel3);
		
		Faculty faculty = new Faculty();
		faculty.setFacultyNameTh("ศึกษาทั่วไป");
		faculty.setFacultyNameEn("general education");
		faculty.setFacultyCodeName("GE");
		faculty.setFacultyNumber("00");
		faculty.setUniversity(university);
		facultyService.saveFaculty(faculty);
		
		Program program1 = new Program();
		program1.setProgramNameTh("ศึกษาทั่วไป");
		program1.setProgramNameEn("general education");
		program1.setProgramCodeName("GE");
		program1.setFaculty(faculty);
		program1.setDegreeLevel(degreeLevel1);
		programService.saveProgram(program1);
		
		Program program2 = new Program();
		program2.setProgramNameTh("ศึกษาทั่วไป");
		program2.setProgramNameEn("general education");
		program2.setProgramCodeName("GE");
		program2.setFaculty(faculty);
		program2.setDegreeLevel(degreeLevel2);
		programService.saveProgram(program2);
		
		Program program3 = new Program();
		program3.setProgramNameTh("ศึกษาทั่วไป");
		program3.setProgramNameEn("general education");
		program3.setProgramCodeName("GE");
		program3.setFaculty(faculty);
		program3.setDegreeLevel(degreeLevel3);
		programService.saveProgram(program3);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลมหาวิทยาลัย");
		logService.saveLog(log);
	}
	
	public void updateUniversity(UniversityUpdatePayload body) throws UniversityException, IOException, AddressException, MemberException, LogException{
		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
		String universityNameThOld = university.getUniversityNameTh();
		String universityNameEnOld = university.getUniversityNameEn();
		String universityCodeNameOld = university.getUniversityCodeName();
		
		String universityCodeName = body.getUniversityCodeName();
		String universityNameTh = body.getUniversityNameTh();
		String universityNameEn = body.getUniversityNameEn();
		
		if(!universityCodeNameOld.equals(universityCodeName)){
			if(universityService.getUniversityCodeName(universityCodeName)) {
				throw UniversityException.universityCodeNameUsed();
			}
		}
		if(!universityNameThOld.equals(universityNameTh)){
			if(universityService.getUniversityNameTh(universityNameTh)) {
				throw UniversityException.universityNameThUsed();
			}
		}
		if(!universityNameEnOld.equals(universityNameEn)){
			if(universityService.getUniversityNameEn(universityNameEn)) {
				throw UniversityException.universityNameEnUsed();
			}
		}
		
		if(universityNameTh == null || universityNameTh.equals("")) {
			throw UniversityException.universityNameThEmpty();
		}
		if(universityNameEn == null || universityNameEn.equals("")) {
			throw UniversityException.universityNameEnEmpty();
		}
		
		university.setUniversityCodeName(universityCodeName);
		university.setUniversityNameTh(universityNameTh);
		university.setUniversityNameEn(universityNameEn);
		
		if(body.getPic() != null) {
			MultipartFile pic = body.getPic();
			
			System.out.println("getSize : "+pic.getSize());
			
			if (Objects.nonNull(pic.getContentType())) {
				String farmImageName = fileStorageService.storeImageFile(pic, TypeImage.UNIVERSITY);
				university.setUniversityImg(farmImageName);
			}		
		}
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
		member.setUniversity(university);
		
		universityService.saveUniversity(university);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลมหาวิทยาลัย");
		logService.saveLog(log);
		
	}
	
	public UniversityJson viewUniversity () throws UniversityException{
		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
		return  UniversityJson.packUniversityJson(university);
	}
}
