package com.example.demo.university.payload.creditTotal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreditTotalUpdatePayload {
	
	private String creditTotalUid;
	private String memberUid;
	private String total;
	private String creditCriterionUid;

}
