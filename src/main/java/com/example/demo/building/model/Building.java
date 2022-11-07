package com.example.demo.building.model;
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

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	private long buildingId;
	
	@Column(name = "building_uuid")
	private String buildingUuid = UUID.randomUUID().toString();
	
	@Column(name = "building_name")
	private String buildingName;
	
	@Column(name = "building_code_name")
	private String buildingCodeName;
	
	@Column(name = "floor_amount")
	private int floorAmount;
	
	@Column(name = "room_amount")
	private int roomAmount;
	
	@ManyToOne
	@JoinColumn(name = "building_type_id")
	private BuildingType buildingType;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "building_status")
	private int buildingStatus;
}