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

import lombok.Data;

@Data
@Entity
@Table(name = "course_structure_det")
public class CourseStructureDet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_structure_det_id")
	private long courseStructureDetId;
	
	@Column(name = "course_structure_det_uuid")
	private String courseStructureDetUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_structure_id")
	private CourseStructure courseStructure;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
}
