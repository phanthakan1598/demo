package com.example.demo.security.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendErrorResponseJson {
	private int status;
	private String path;
	private String message;
}
