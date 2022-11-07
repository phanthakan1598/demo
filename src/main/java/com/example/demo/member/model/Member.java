package com.example.demo.member.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.address.model.Address;
import com.example.demo.university.model.University;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private long memberId;
	
	@Column(name = "member_uuid")
	private String memberUuid = UUID.randomUUID().toString();
	
	@Column(name = "member_username")
	private String memberUsername;
	
	@Column(name = "member_password")
	private String memberPassword;
	
	@Column(name = "member_name_th")
	private String memberNameTh;
	
	@Column(name = "member_name_en")
	private String memberNameEn;
	
	@Column(name = "member_birthday")
	private LocalDate memberBirthday;

	@Column(name = "member_tel")
	private String memberTel;
	
	@Column(name = "member_email")
	private String memberEmail;
	
	@Column(name = "member_status")
	private int memberStatus;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "member_img")
	private String memberImg;
}