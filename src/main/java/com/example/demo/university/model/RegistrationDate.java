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
@Table(name = "registration_date")
public class RegistrationDate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_date_id")
	private long registrationDateId;
	
	@Column(name = "registration_date_uuid")
	private String registrationDateUuid = UUID.randomUUID().toString();
	
	@Column(name = "registration_date_name")
	private String registrationDateName;
	
	@Column(name = "registration_date_level_class")
	private int registrationDateLevelClass;
	
	@Column(name = "registration_date_range")
	private int registrationDateRange;
	
	@Column(name = "begin_date")
	private LocalDate beginDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_id")
	private Term term;
	
	@Column(name = "registration_date_type")
	private String registrationDateType;
	
}
