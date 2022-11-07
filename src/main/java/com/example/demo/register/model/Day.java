package com.example.demo.register.model;

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
@Table(name = "day")
public class Day {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_id")
	private long dayId;
	
	@Column(name = "day_uuid")
	private String dayUuid = UUID.randomUUID().toString();
	
	@Column(name = "day_name_th")
	private String dayNameTh;
	
	@Column(name = "day_name_code_th")
	private String dayNameCodeTh;
	
	@Column(name = "day_name_en")
	private String dayNameEn;
	
	@Column(name = "day_name_code_en")
	private String dayNameCodeEn;
}
