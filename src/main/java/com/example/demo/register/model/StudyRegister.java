package com.example.demo.register.model;

import java.time.LocalTime;
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

import com.example.demo.building.model.Room;
import com.example.demo.subject.model.Subject;
import com.example.demo.university.model.Term;

import lombok.Data;

@Data
@Entity
@Table(name = "study_register")
public class StudyRegister {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_register_id")
	private long studyRegisterId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@Column(name = "study_register_uuid")
	private String studyRegisterUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "capacity_max")
	private int capacityMax;
	
	@Column(name = "capacity_total")
	private int capacityTotal;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "day_id")
	private Day day;
	
	@Column(name = "sec")
	private int sec;
	
	@Column(name = "time_begin")
	private LocalTime timeBegin;
	
	@Column(name = "time_end")
	private LocalTime timeEnd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_id")
	private Term term;
}
