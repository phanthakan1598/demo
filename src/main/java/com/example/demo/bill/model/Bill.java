package com.example.demo.bill.model;

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

import com.example.demo.university.model.DegreeLevel;

import lombok.Data;

@Data
@Entity
@Table(name = "bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private long billId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "bill_uuid")
	private String billUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
}
