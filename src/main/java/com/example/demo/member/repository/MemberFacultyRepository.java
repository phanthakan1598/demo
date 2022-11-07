package com.example.demo.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberFaculty;
import com.example.demo.university.model.University;

public interface MemberFacultyRepository extends JpaRepository<MemberFaculty, Long>{
	Optional<MemberFaculty> findOneByMemberFacultyUuid(String memberFacultyUuid);
	Optional<MemberFaculty> findOneByMember(Member member);
	
	Optional<MemberFaculty> findOneByMemberMemberUuid(String memberUid);
	
	boolean existsByMemberRoleRoleNameInAndFaculty(List<String> roleName,Faculty faculty);
	Optional<MemberFaculty> findFirstByMemberRoleRoleNameInAndFacultyOrderByStudentNumberDesc(List<String> roleName,Faculty faculty);

	List<MemberFaculty> findByMemberUniversityAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(University university, List<String> roleName,int memberStatus);
	
	List<MemberFaculty> findByFacultyAndMemberRoleRoleNameInAndMemberMemberStatusNotOrderByMemberUpdatedAtDesc(Faculty faculty, List<String> roleName,int memberStatus);
	
//	List<MemberFaculty> findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc
}
