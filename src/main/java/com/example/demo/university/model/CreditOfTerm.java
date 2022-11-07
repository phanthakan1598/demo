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

import com.example.demo.faculty.model.Program;

import lombok.Data;

@Data
@Entity
@Table(name = "credit_of_term")
public class CreditOfTerm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_of_term_id")
	private long creditOfTermId;
	
	@Column(name = "credit_min")
	private int creditMin;
	
	@Column(name = "credit_max")
	private int creditMax;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id")
	private Program program;
	
	@Column(name = "credit_of_term_uuid")
	private String creditOfTermUuid = UUID.randomUUID().toString();
}
