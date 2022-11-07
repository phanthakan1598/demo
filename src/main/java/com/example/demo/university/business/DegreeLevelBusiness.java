package com.example.demo.university.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.exception.LogException;
import com.example.demo.member.model.Log;
import com.example.demo.member.service.LogService;
import com.example.demo.security.util.JWTAuth;
import com.example.demo.university.exception.DegreeLevelException;
import com.example.demo.university.json.DegreeLevelAllJson;
import com.example.demo.university.model.DegreeLevel;
import com.example.demo.university.model.University;
import com.example.demo.university.payload.degreeLevel.DegreeLevelInsertPayload;
import com.example.demo.university.payload.degreeLevel.DegreeLevelUpdatePayload;
import com.example.demo.university.service.DegreeLevelService;

import lombok.Setter;

@Service
@Setter
public class DegreeLevelBusiness {
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	DegreeLevelService degreeLevelService;
	
	@Autowired
	LogService logService;
	
	public void insertDegreeLevel(DegreeLevelInsertPayload body) throws DegreeLevelException, LogException{
		
		if(body.getName() == null || body.getName().isEmpty()) {
			throw DegreeLevelException.degreeLevelNameEmpty();
		}
		
		DegreeLevel degreeLevelModel = new DegreeLevel();
		
		degreeLevelModel.setName(body.getName());
		degreeLevelModel.setUniversity(jwtAuth.getCurrentUser().getUniversity());
		
		degreeLevelService.saveDegreeLevel(degreeLevelModel);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลระดับปริญญา");
		logService.saveLog(log);
		
	}
	
	public void updateDegreeLevel(DegreeLevelUpdatePayload body) throws DegreeLevelException, LogException {
		
		if(body.getName() == null || body.getName().isEmpty()) {
			throw DegreeLevelException.degreeLevelNameEmpty();
		}
		
		if(body.getDegreeLevelUid() == null || body.getDegreeLevelUid().isEmpty()) {
			throw DegreeLevelException.degreeLevelUuidEmpty();
		}
		
		DegreeLevel degreeLevelModel = degreeLevelService.findDegreeLevelByUuid(body.getDegreeLevelUid());
		
		degreeLevelModel.setName(body.getName());
		
		degreeLevelService.saveDegreeLevel(degreeLevelModel);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลระดับปริญญา");
		logService.saveLog(log);
		
	}
	
	public void deleteDegreeLevel(String uuid) throws DegreeLevelException, LogException {
		if(uuid == null || uuid.isEmpty()) {
			throw DegreeLevelException.degreeLevelUuidEmpty();
		}
		
		DegreeLevel degreeLevelModel = degreeLevelService.findDegreeLevelByUuid(uuid);
		
		degreeLevelService.delDegreeLevel(degreeLevelModel);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลระดับปริญญา");
		logService.saveLog(log);
	}

	public List<DegreeLevelAllJson> viewAllDegreeLevel(){
		University university =  jwtAuth.getCurrentUser().getUniversity();
		return  DegreeLevelAllJson.degreeLevelCourseAllJsons(degreeLevelService.getAllDegreeLevel(university));
	}

}
