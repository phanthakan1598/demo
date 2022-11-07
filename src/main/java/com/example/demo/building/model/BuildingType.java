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

import com.example.demo.university.model.University;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "building_type")
public class BuildingType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_type_id")
	private long buildingTypeId;
	
	@Column(name = "building_type_uuid")
	private String buildingTypeUuid = UUID.randomUUID().toString();
	
	@Column(name = "building_type_name")
	private String buildingTypeName;
	
	@ManyToOne
	@JoinColumn(name = "university_id")
	private University university;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;
}