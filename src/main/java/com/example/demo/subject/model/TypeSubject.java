package com.example.demo.subject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "type_subject")
public class TypeSubject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_subject_id")
	private long typeSubjectId;
	
	@Column(name = "type_subject_uuid")
	private String typeSubjectUuid = UUID.randomUUID().toString();
	
	@Column(name = "type_subject_name_th")
	private String typeSubjectNameTh;
	
	@Column(name = "type_subject_name_en")
	private String typeSubjectNameEn;
	
}
