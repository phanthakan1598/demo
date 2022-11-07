package com.example.demo.university.model;

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
@Table(name = "degree_level")
public class DegreeLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "degree_level_id")
	private long degreeLevelId;
	
	@Column(name = "degree_level_uuid")
	private String degreeLevelUuid = UUID.randomUUID().toString();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "degree_level_number")
	private String degreeLevelNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;
}
