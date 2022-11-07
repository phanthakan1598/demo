package com.example.demo.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.model.Member;
import com.example.demo.university.model.University;

public interface MemberRepository extends JpaRepository<Member, Long>{
		Optional<Member> findOneByMemberUsername(String memberUsername);
		Optional<Member> findOneByMemberId(long memberId);
		Optional<Member> findOneByMemberUuid(String memberUuid);
		Optional<Member> findOneByMemberEmail(String memberEmail);
		
		boolean existsByMemberEmail(String memberEmail);
		boolean existsByMemberUsername(String memberUsername);
		
//		<T> getOneByMemberEmail(String memberEmail);
		boolean existsByMemberEmailAndMemberStatus(String memberEmail, int memberStatus);
//		boolean existsByMemberEmail(String memberEmail);
//		Optional<Member> findByRole(Role role);
//		ProvinceUuid);
//		List<Member> findByUniversityAndRoleRoleNameInAndMemberStatusNot(University university,List<String> roleName,int memberStatus);
//		List<Member> findByUniversityAndRoleRoleNameNotInAndMemberStatusNot(University university, List<String> roleName,int memberStatus);
//		List<Member> findByUniversityAndRoleRoleNameInAndMemberStatusNot(University university,List<String> roleName,int memberStatus);
		List<Member> findByUniversityAndRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(University university, List<String> roleName,int memberStatus);
		List<Member> findByUniversityAndRoleRoleNameNotInAndMemberStatusNotOrderByUpdatedAtDesc(University university, List<String> roleName,int memberStatus);
		List<Member> findByRoleRoleNameInAndMemberStatusNotOrderByUpdatedAtDesc(List<String> roleName,int memberStatus);
		
		
//		List<Member> findRoleIdBetween(long role1,long role2);
		int countByRoleRoleNameInAndMemberStatusNotAndUniversityUniversityNameEnNot(List<String> roleName,int memberStatus,String universityName);
		List<Member> findByRoleRoleNameInAndMemberStatusNotAndUniversityUniversityNameEnNotOrderByUpdatedAtDesc(List<String> roleName,int memberStatus,String universityName);
		
//		@Query("select new com.example.Count(Member.member_status, u.university_name_en, r.region_name_en, count(*) )"
//				+ "from Member"
//				+ "inner join university u on m.university_id = u.university_id"
//				+ "inner join tambon t on u.tambon_id = t.tambon_id"
//				+ "inner join amphur a on t.amphur_id = a.amphur_id"
//				+ "inner join province p on a.province_id = p.province_id"
//				+ "inner join region r on p.region_id = r.region_id"
//				+ "where m.member_status <> 2"
//				+ "and u.university_name_en <> 'default university' "
//				+ "group by r.region_name_en")
//		public List<Count> getCountByCountryAndState();
		
		<T>Optional<T> findOneByMemberUsername(String memberUsername, Class<T> type);
		<T>Optional<T> findOneByMemberUuid(String memberUuid, Class<T> type);
		
}
