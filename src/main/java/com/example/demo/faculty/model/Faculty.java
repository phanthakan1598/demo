package com.example.demo.faculty.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.university.model.University;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "faculty")
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private long facultyId;
	
	@Column(name = "faculty_uuid")
	private String facultyUuid = UUID.randomUUID().toString();
	
	@Column(name = "faculty_name_th")
	private String facultyNameTh;
	
	@Column(name = "faculty_name_en")
	private String facultyNameEn;
	
	@Column(name = "faculty_code_name")
	private String facultyCodeName;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "faculty_number")
	private String facultyNumber;
}
