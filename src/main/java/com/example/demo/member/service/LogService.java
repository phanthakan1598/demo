package com.example.demo.member.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.repository.LogRepository;

import lombok.Setter;

@Service
@Setter
public class LogService {
	@Autowired
	LogRepository logRepository;
	
	public Log findLogByUuid(String uuid) throws LogException {
        return logRepository.findOneByLogUuid(uuid).orElseThrow(LogException::logNotFound);
    }
	
	public Log saveLog(Log log) throws LogException {
    	if(Objects.isNull(log)) {
    		throw LogException.logIsEmptyOrNull();
    	}
		return logRepository.save(log);
	}
	
	public void delLog(Log log) throws LogException {
    	if(Objects.isNull(log)) {
    		throw LogException.logIsEmptyOrNull();
    	}
    	logRepository.delete(log);
	}
	
	public List<Log> logs() {
		return logRepository.findByOrderByCreateAtDesc();
	}
}
