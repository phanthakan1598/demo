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
@Table(name = "province")
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id")
	private long provinceId;
	
	@Column(name = "province_uuid")
	private String provinceUuid = UUID.randomUUID().toString();
	
	@Column(name = "province_name_th")
	private String provinceNameTh;
	
	@Column(name = "province_name_en")
	private String provinceNameEn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;
}
