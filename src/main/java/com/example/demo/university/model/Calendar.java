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
@Table(name = "calendar")
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_id")
	private long calendarId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "begin_date")
	private LocalDate beginDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_id")
	private Term term;
	
	@Column(name = "calendar_uuid")
	private String calendarUuid = UUID.randomUUID().toString();
	
}
