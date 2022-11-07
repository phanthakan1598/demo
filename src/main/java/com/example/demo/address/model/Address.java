package com.example.demo.address.model;

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

import com.example.demo.member.model.Member;

import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;
	
	@Column(name = "address_uuid")
	private String addressUuid = UUID.randomUUID().toString();
	
	@Column(name = "address_detail_th")
	private String addressDetailTh;
	
	@Column(name = "address_detail_en")
	private String addressDetailEn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tambon_id")
	private Tambon tambon;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
