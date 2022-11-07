package com.example.demo.member.model;

import java.io.Serializable;
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
@Table(name = "role")
public class Role implements Serializable{
	
	private static final long serialVersionUID = -8836731784728081350L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private long roleId;
	
	@Column(name = "role_uuid")
	private String roleUuid = UUID.randomUUID().toString();
	
	@Column(name = "role_name")
	private String roleName;
}
