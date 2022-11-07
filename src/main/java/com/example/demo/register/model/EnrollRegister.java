package com.example.demo.register.model;

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

import com.example.demo.bill.model.BillProgram;
import com.example.demo.member.model.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "enroll_register")
public class EnrollRegister {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enroll_register_id")
	private long enrollRegisterId;
	
	@Column(name = "enroll_register_uuid")
	private String enrollRegisterUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bill_program_id")
	private BillProgram billProgram;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_register_id")
	private StudyRegister studyRegister;
	
	@Column(name = "enroll_register_status")
	private int enrollRegisterStatus;
	
}
