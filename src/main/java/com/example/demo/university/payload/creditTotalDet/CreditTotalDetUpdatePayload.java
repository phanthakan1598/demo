package com.example.demo.university.payload.creditTotalDet;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreditTotalDetUpdatePayload {
	private String creditTotalDetUid;
	private int creditAdd;
	private String year;
	private int term;
	private String memberUid;
	private String creditOfTermUid;
}