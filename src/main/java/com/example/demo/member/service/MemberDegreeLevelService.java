package com.example.demo.member.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.exception.MemberDegreeLevelException;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDegreeLevel;
import com.example.demo.member.repository.MemberDegreeLevelRepository;
import com.example.demo.university.model.University;

import lombok.Setter;

@Service
@Setter
public class MemberDegreeLevelService {
	@Autowired
	MemberDegreeLevelRepository memberDegreeLevelRepository;
	
	public MemberDegreeLevel findMemberDegreeLevelByUuid(String uuid) throws MemberDegreeLevelException {
        return memberDegreeLevelRepository.findOneByMemberDegreeLevelUuid(uuid).orElseThrow(MemberDegreeLevelException::memberDegreeLevelNotFound);
    }
	
	public MemberDegreeLevel findMember(Member member) throws MemberDegreeLevelException {
        return memberDegreeLevelRepository.findOneByMember(member).orElseThrow(MemberDegreeLevelException::memberDegreeLevelNotFound);
    }
	
	public MemberDegreeLevel saveMemberDegreeLevel(MemberDegreeLevel memberDegreeLevel) throws MemberDegreeLevelException {
    	if(Objects.isNull(memberDegreeLevel)) {
    		throw MemberDegreeLevelException.memberDegreeLevelIsEmptyOrNull();
    	}
		return memberDegreeLevelRepository.save(memberDegreeLevel);
	}
	
	public void delMemberDegreeLevel(MemberDegreeLevel memberDegreeLevel) throws MemberDegreeLevelException {
    	if(Objects.isNull(memberDegreeLevel)) {
    		throw MemberDegreeLevelException.memberDegreeLevelIsEmptyOrNull();
    	}
    	memberDegreeLevelRepository.delete(memberDegreeLevel);
	}
	
	public List<MemberDegreeLevel> getAllMemberDegreeLevel (University university){
		return memberDegreeLevelRepository.findByDegreeLevelUniversity(university);
	}
	
}
