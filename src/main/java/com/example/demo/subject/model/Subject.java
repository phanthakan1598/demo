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
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private long subjectId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "credit")
	private int credit;
	
	@Column(name = "subject_uuid")
	private String subjectUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_subject_id")
	private GroupSubject groupSubject;
	
	@Column(name = "subject_code")
	private String subjectCode;
}
