package com.example.demo.member.model;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.university.model.DegreeLevel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member_degree_level")
public class MemberDegreeLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_degree_level_id")
	private long memberDegreeLevelId;
	
	@Column(name = "member_degree_level_uuid")
	private String memberDegreeLevelUuid = UUID.randomUUID().toString();
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "degree_level_id")
	private DegreeLevel degreeLevel;
}