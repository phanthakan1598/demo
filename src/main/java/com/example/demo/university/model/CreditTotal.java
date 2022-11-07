//package com.example.demo.university.model;
//
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
//import com.example.demo.member.model.Member;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "credit_total")
//public class CreditTotal {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "credit_total_id")
//	private long creditTotalId;
//	
//	@Column(name = "credit_total_uuid")
//	private String creditTotalUuid = UUID.randomUUID().toString();
//	
//	@Column(name = "total")
//	private int total;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "member_id")
//	private Member member;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "credit_criterion_id")
//	private CreditCriterion creditCriterion;
//}
