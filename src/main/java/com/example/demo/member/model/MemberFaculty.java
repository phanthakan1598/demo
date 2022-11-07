package com.example.demo.member.model;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.faculty.model.Faculty;
import com.example.demo.faculty.model.Program;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member_faculty")
public class MemberFaculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_faculty_id")
	private long memberFacultyId;
	
	@Column(name = "member_faculty_uuid")
	private String memberFacultyUuid = UUID.randomUUID().toString();
	
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;
	
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "student_number")
	private String studentNumber;
	
	
	
}