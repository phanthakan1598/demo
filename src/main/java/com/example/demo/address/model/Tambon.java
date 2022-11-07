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
@Table(name = "tambon")
public class Tambon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tambon_id")
	private long tambonId;
	
	@Column(name = "tambon_uuid")
	private String tambonUuid = UUID.randomUUID().toString();
	
	@Column(name = "tambon_zip_code")
	private int tambonZipCode;
	
	@Column(name = "tambon_name_th")
	private String tambonNameTh;
	
	@Column(name = "tambon_name_en")
	private String tambonNameEn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "amphur_id")
	private Amphur amphur;
}
