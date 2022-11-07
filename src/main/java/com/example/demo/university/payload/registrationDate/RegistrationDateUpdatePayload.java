package com.example.demo.university.payload.registrationDate;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationDateUpdatePayload {
	private String registrationDateUuid;
	private String levelClass;
	private String range;
	private LocalDate beginDate;
	private LocalDate endDate;
	private String type;
}
