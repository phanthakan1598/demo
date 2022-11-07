package com.example.demo.register.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.register.exception.DayException;
import com.example.demo.register.model.Day;
import com.example.demo.register.repository.DayRepository;

import lombok.Setter;

@Service
@Setter
public class DayService {
	@Autowired
	DayRepository dayRepository;
	
	public Day findDayByUuid(String uuid) throws DayException {
        return dayRepository.findOneByDayUuid(uuid).orElseThrow(DayException::dayNotFound);
    }
	
	public List<Day> days (){
		return dayRepository.findAll();
	}
}
