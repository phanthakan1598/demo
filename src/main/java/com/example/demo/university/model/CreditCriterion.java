//package com.example.demo.university.model;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.example.demo.faculty.model.Program;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "credit_criterion")
//public class CreditCriterion {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "credit_criterion_id")
//	private long creditCriterionId;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "total_max")
//	private int totalMax;
//	
//	@Column(name = "credit_criterion_uuid")
//	private String creditCriterionUuid = UUID.randomUUID().toString();
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "program_id")
//	private Program program;
//	
//	@Column(name = "created_at")
//	private LocalDateTime createdAt;
//	
//	@Column(name = "updated_at")
//	private LocalDateTime updatedAt;
//}
