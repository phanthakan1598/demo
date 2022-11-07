package com.example.demo.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.university.business.DegreeLevelBusiness;
import com.example.demo.university.json.DegreeLevelAllJson;

@RestController
@RequestMapping("/degreeLevel")
public class DegreeLevelController {
	
	@Autowired
	DegreeLevelBusiness degreeLevelBusiness;
	
	@GetMapping(value = "/viewAllDegreeLevel")
	public ResponseEntity<List<DegreeLevelAllJson>> viewAllDegreeLevel(){
		return ResponseEntity.ok(degreeLevelBusiness.viewAllDegreeLevel());
	}
	
}
