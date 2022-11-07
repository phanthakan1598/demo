package com.example.demo.subject.payload.courseStructure;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseStructureInsertPayload {
	private String programUuid;
	private String name;
	private String courseStructureGeCredits;
	private String courseStructureMajorCredits;
}
