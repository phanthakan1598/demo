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

import com.example.demo.university.model.DegreeLevel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "program")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "program_id")
	private long programId;
	
	@Column(name = "program_uuid")
	private String programUuid = UUID.randomUUID().toString();
	
	@Column(name = "program_name_th")
	private String programNameTh;
	
	@Column(name = "program_name_en")
	private String programNameEn;
	
	@Column(name = "program_code_name")
	private String programCodeName;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@ManyToOne
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
