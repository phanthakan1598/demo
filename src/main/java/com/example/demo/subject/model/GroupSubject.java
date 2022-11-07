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

import com.example.demo.faculty.model.Faculty;
import com.example.demo.university.model.DegreeLevel;

import lombok.Data;

@Data
@Entity
@Table(name = "group_subject")
public class GroupSubject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_subject_id")
	private long groupSubjectId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_subject_id")
	private TypeSubject typeSubject;
	
	@Column(name = "group_subject_uuid")
	private String groupSubjectUuid = UUID.randomUUID().toString();
	
	
}
