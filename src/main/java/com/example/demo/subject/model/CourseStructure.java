package com.example.demo.subject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.faculty.model.Program;

import lombok.Data;

@Data
@Entity
@Table(name = "course_structure")
public class CourseStructure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_structure_id")
	private long courseStructureId;
	
	@Column(name = "course_structure_uuid")
	private String courseStructureUuid = UUID.randomUUID().toString();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "course_structure_ge_credits")
	private int courseStructureGeCredits;
	
	@Column(name = "course_structure_major_credits")
	private int courseStructureMajorCredits;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id")
	private Program program;
	
}
