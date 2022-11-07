package com.example.demo.university.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.address.service.AddressService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.UniversityException;
import com.example.demo.university.model.University;
import com.example.demo.university.repository.UniversityRepository;

import lombok.Setter;

@Service
@Setter
public class UniversityService {
	
	@Autowired
	UniversityRepository universityRepository;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	MemberService memberService;
	
	public boolean getUniversityNameTh(String nameTh){
        return universityRepository.existsByUniversityNameTh(nameTh);
    }
	
	public boolean getUniversityNameEn(String nameEn){
        return universityRepository.existsByUniversityNameEn(nameEn);
    }
	
	public boolean getUniversityCodeName(String codeName){
        return universityRepository.existsByUniversityCodeName(codeName);
    }
	
	
//	public University insertUniversity(UniversityPayload universityPayload) throws MemberException{
//		University university = new University();
//		Tambon tambon = addressService.getTambon(universityPayload.getTambonUuid());
////		System.out.println("tambon : "+tambon.get());
//		university.setUniversityNameTh(universityPayload.getUniversityNameTh());
//		university.setUniversityNameEn(universityPayload.getUniversityNameEn());
//		university.setUniversityAddress(universityPayload.getUniversityAddress());
//		university.setTambon(tambon);
//		
////		universityRepository.save(university);
////		
////		System.out.println("tambon : "+university);
//		
//		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
//		member.setUniversity(university);
//		saveUniversity(university);
//		return null;
////		return 
//	}
	
//	public Optional<University> getUniversity(String UniversityUuid){
//		return universityRepository.findOneByUniversityUuid(UniversityUuid);
//	}
	
	public University saveUniversity(University university) throws UniversityException {
    	if(Objects.isNull(university)) {
    		throw UniversityException.universityIsEmptyOrNull();
    	}
		return universityRepository.save(university);
	}
	
	public void delUniversity(University university) throws UniversityException {
    	if(Objects.isNull(university)) {
    		throw UniversityException.universityIsEmptyOrNull();
    	}
		universityRepository.delete(university);
	}
	
//	public University UpdataUniversity(UniversityUpdatePayload body) throws MemberException {
//			Tambon tambon = addressService.getTambon(body.getTambonUuid());
//			University university = findUniversityByUuid(body.getUniversityUuid());
//			university.setUniversityNameTh(body.getUniversityNameTh());
//			university.setUniversityNameEn(body.getUniversityNameEn());
//			university.setUniversityAddress(body.getUniversityAddress());
//			university.setTambon(tambon);
//			return saveUniversity(university);
//		}
//	
	
	public University findUniversityByUuid(String uuid) throws UniversityException {
        return universityRepository.findOneByUniversityUuid(uuid).orElseThrow(UniversityException::universityNotFound);
    }
	
	public University findFirstUniversityNumberDesc() throws UniversityException {
        return universityRepository.findFirstByOrderByUniversityNumberDesc().orElseThrow(UniversityException::universityNotFound);
    }
	
//	public UniversityJson getUniversityByUuid(String uuid) throws MemberException{
//		return UniversityJson.packUniversityJson(findUniversityByUuid(uuid));
//	}
	
//	public University insertUniversity(University university) {
//        return universityRepository.save(university);
//    }
}
