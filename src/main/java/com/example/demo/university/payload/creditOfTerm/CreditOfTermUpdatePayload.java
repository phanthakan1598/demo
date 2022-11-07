package com.example.demo.university.payload.creditOfTerm;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreditOfTermUpdatePayload {
	
	private String creditOfTermUid;
	private String creditMin;
	private String creditMax;
}
