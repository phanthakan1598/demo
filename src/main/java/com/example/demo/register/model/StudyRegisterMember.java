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

import com.example.demo.member.model.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "study_register_member")
public class StudyRegisterMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_register_member_id")
	private long studyRegisterMemberId;
	
	@Column(name = "study_register_member_uuid")
	private String studyRegisterMemberUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_register_id")
	private StudyRegister studyRegister;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
}
