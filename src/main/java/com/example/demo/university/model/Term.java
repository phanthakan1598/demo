package com.example.demo.university.model;

import java.time.LocalDate;
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
@Table(name = "term")
public class Term {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "term_id")
	private long termId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
	
	@Column(name = "term")
	private int term;
	
	@Column(name = "begin_date")
	private LocalDate beginDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "term_uuid")
	private String termUuid = UUID.randomUUID().toString();
	
}
