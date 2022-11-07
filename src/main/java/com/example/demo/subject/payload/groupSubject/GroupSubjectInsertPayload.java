package com.example.demo.subject.payload.groupSubject;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupSubjectInsertPayload {
	private String degreeLevelUid;
	private String facultyUuid;
	private String name;
	private String typeSubjectUuid;
}
