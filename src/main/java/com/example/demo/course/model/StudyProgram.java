package com.example.demo.course.model;

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

import com.example.demo.subject.model.CourseStructure;

import lombok.Data;

@Data
@Entity
@Table(name = "study_program")
public class StudyProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_program_id")
	private long studyProgramId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "study_program_uuid")
	private String studyProgramUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_structure_id")
	private CourseStructure courseStructure;
}