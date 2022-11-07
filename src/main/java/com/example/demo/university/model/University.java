package com.example.demo.university.model;

import java.time.LocalDateTime;
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

import com.example.demo.address.model.Tambon;

import lombok.Data;

@Data
@Entity
@Table(name = "university")
public class University {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "university_id")
	private long universityId;
	
	@Column(name = "university_uuid")
	private String universityUuid = UUID.randomUUID().toString();
	
	@Column(name = "university_name_th")
	private String universityNameTh;
	
	@Column(name = "university_name_en")
	private String universityNameEn;
	
	@Column(name = "university_address")
	private String universityAddress;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tambon_id")
	private Tambon tambon;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "university_img")
	private String universityImg;
	
	@Column(name = "university_number")
	private String universityNumber;
	
	@Column(name = "university_code_name")
	private String universityCodeName;
}
