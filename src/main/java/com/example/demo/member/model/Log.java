package com.example.demo.member.model;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private long logId;
	
	@Column(name = "log_uuid")
	private String logUuid = UUID.randomUUID().toString();
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(name = "log_event")
	private String logEvent;
	
	@Column(name = "create_at")
	private LocalDateTime createAt;
}