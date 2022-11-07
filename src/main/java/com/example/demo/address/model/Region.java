package com.example.demo.address.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "region")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private long regionId;
	
	@Column(name = "region_uuid")
	private String regionUuid = UUID.randomUUID().toString();
	
	@Column(name = "region_name_th")
	private String regionNameTh;
	
	@Column(name = "region_name_en")
	private String regionNameEn;
	
}
