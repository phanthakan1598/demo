package com.example.demo.register.payload.enrollRegister;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnrollRegisterUpdatePayload {
	private String enrollRegisterUid;
	private String memberUid;
	private String billProgramUid;
	private String studyRegisterUid;
}