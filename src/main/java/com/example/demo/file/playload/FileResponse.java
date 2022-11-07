package com.example.demo.file.playload;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FileResponse {
	String fileNames;
	String messages;
	
	public static FileResponse packFileResponse(String fileName, String message) {
		FileResponse fileResponse = new FileResponse();
		fileResponse.setFileNames(fileName);
		fileResponse.setMessages(message);
		return fileResponse;
	}
}
