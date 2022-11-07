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
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private long roomId;
	
	@Column(name = "room_uuid")
	private String roomUuid = UUID.randomUUID().toString();
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "floor_number")
	private String floorNumber;
	
	@Column(name = "room_number")
	private String roomNumber;
	
	@Column(name = "room_code_name")
	private String roomCodeName;
	
	@Column(name = "room_capacity")
	private int roomCapacity;
	
	@ManyToOne
	@JoinColumn(name = "building_id")
	private Building building;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "room_status")
	private int roomStatus;
}