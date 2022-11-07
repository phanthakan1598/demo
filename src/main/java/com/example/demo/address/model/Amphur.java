package com.example.demo.address.model;

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


import lombok.Data;

@Data
@Entity
@Table(name = "amphur")
public class Amphur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "amphur_id")
	private long amphurId;
	
	@Column(name = "amphur_uuid")
	private String amphurUuid = UUID.randomUUID().toString();
	
	@Column(name = "amphur_name_th")
	private String amphurNameTh;
	
	@Column(name = "amphur_name_en")
	private String amphurNameEn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
	private Province province;
}
