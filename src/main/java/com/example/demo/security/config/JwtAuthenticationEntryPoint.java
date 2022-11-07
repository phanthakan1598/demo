package com.example.demo.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.security.json.DefaultResponseJson;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.version}")
	private String appVersion;
	
	@Value("${app.build}")
	private String appBuild;
	
	@Value("${app.env}")
	private String appEnv;
	
	 @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
	        
	    	DefaultResponseJson json = new DefaultResponseJson();
	    	ObjectMapper mapper = new ObjectMapper();
	    	
			json.setStatus(HttpStatus.UNAUTHORIZED.value());
			json.setApp(appName);
			json.setVersion(appVersion + "-" + appBuild + "-" + appEnv);
			
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    	
	        response.getWriter().write(mapper.writeValueAsString(json));
	    }
}
