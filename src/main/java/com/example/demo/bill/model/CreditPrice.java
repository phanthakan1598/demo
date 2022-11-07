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
@Table(name = "credit_price")
public class CreditPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_price_id")
	private long creditPriceId;
	
	@Column(name = "credit_price")
	private float creditPrice;
	
	@Column(name = "credit_price_uuid")
	private String creditPriceUuid = UUID.randomUUID().toString();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
}
