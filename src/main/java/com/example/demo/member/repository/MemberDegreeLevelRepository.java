package com.example.demo.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDegreeLevel;
import com.example.demo.university.model.University;

public interface MemberDegreeLevelRepository extends JpaRepository<MemberDegreeLevel, Long>{
	Optional<MemberDegreeLevel> findOneByMember(Member member);
	Optional<MemberDegreeLevel> findOneByMemberDegreeLevelUuid(String memberDegreeLevelUuid);
	List<MemberDegreeLevel> findByDegreeLevelUniversity(University university);
}
