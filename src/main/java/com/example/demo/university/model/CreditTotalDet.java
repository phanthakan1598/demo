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

import com.example.demo.member.model.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "credit_total_det")
public class CreditTotalDet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_total_det_id")
	private long creditTotalDetId;
	
	@Column(name = "credit_total_det_uuid")
	private String creditTotalDetUuid = UUID.randomUUID().toString();
	
	@Column(name = "credit_add")
	private int creditAdd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_of_term_id")
	private CreditOfTerm creditOfTerm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "term_id")
	private Term term;
	
}
