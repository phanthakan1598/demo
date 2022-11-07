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

import com.example.demo.subject.model.TypeSubject;

import lombok.Data;

@Data
@Entity
@Table(name = "study_program_det")
public class StudyProgramDet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_program_det_id")
	private long studyProgramDetId;
	
	@Column(name = "term")
	private String term;
	
	@Column(name = "level_class")
	private int levelClass;
	
	@Column(name = "study_program_det_uuid")
	private String studyProgramDetUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_program_id")
	private StudyProgram studyProgram;
	
	@Column(name = "subject_code")
	private String subjectCode;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_subject_id")
	private TypeSubject typeSubject;
}
