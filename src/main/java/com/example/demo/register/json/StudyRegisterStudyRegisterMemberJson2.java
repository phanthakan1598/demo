//package com.example.demo.register.json;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.example.demo.course.exception.CourseGroupDetException;
//import com.example.demo.course.exception.GeSubjectGroupDetException;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//public class StudyRegisterStudyRegisterMemberJson {
//	
//	String subjectId;
//	String subjectName;
//	String sec;
//	String type;
//	String capacityMax;
//	String capacityTotal;
//	String memberName;
//	String dayName;
//	String begin;
//	String end;
//	String roomName;
//	String buildingName;
//	
//	
//	
//	public static StudyRegisterStudyRegisterMemberJson packStudyRegisterStudyRegisterMemberJson(HashMap<String, HashMap<String, Object>> dayGroupMain) throws CourseGroupDetException, GeSubjectGroupDetException {
//		StudyRegisterStudyRegisterMemberJson studyRegisterStudyRegisterMemberJson = new StudyRegisterStudyRegisterMemberJson();
//		
//		for(int i = 0; i < dayGroupMain.size(); i++) {
//			
//			if(dayGroupMain.get("sunday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("sunday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("sunday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("sunday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("sunday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("sunday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("sunday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("sunday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("sunday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("sunday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("sunday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("sunday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("sunday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("monday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("monday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("monday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("monday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("monday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("monday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("monday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("monday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("monday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("monday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("monday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("monday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("monday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("tuesday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("tuesday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("tuesday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("tuesday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("tuesday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("tuesday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("tuesday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("tuesday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("tuesday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("tuesday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("tuesday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("tuesday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("tuesday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("wednesday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("wednesday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("wednesday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("wednesday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("wednesday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("wednesday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("wednesday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("wednesday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("wednesday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("wednesday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("wednesday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("wednesday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("wednesday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("thursday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("thursday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("thursday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("thursday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("thursday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("thursday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("thursday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("thursday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("thursday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("thursday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("thursday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("thursday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("thursday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("friday") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("friday").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("friday").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("friday").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("friday").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("friday").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("friday").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("friday").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("friday").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("friday").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("friday").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("friday").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("friday").get("building_name").toString());
//				
//			} else if(dayGroupMain.get("saturay") != null) {
//				
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("saturay").get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("saturay").get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("saturay").get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("saturay").get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("saturay").get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("saturay").get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("saturay").get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("saturay").get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("saturay").get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("saturay").get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("saturay").get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("saturay").get("building_name").toString());
//			}
//		}
//		
//		
//		return studyRegisterStudyRegisterMemberJson;
//	}
//	
//	public static StudyRegisterStudyRegisterMemberJson packStudyRegisterStudyRegisterMemberJsonSuper(HashMap<String, Object> dayGroupMain) throws CourseGroupDetException, GeSubjectGroupDetException {
//		StudyRegisterStudyRegisterMemberJson studyRegisterStudyRegisterMemberJson = new StudyRegisterStudyRegisterMemberJson();
//		
//		for(int i = 0; i < dayGroupMain.size(); i++) {
//			
//				studyRegisterStudyRegisterMemberJson.setSubjectId(dayGroupMain.get("subject_id").toString());
//				studyRegisterStudyRegisterMemberJson.setSubjectName(dayGroupMain.get("name").toString());
//				studyRegisterStudyRegisterMemberJson.setSec(dayGroupMain.get("sec").toString());
//				studyRegisterStudyRegisterMemberJson.setType(dayGroupMain.get("type").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityMax(dayGroupMain.get("capacity_max").toString());
//				studyRegisterStudyRegisterMemberJson.setCapacityTotal(dayGroupMain.get("capacity_total").toString());
//				studyRegisterStudyRegisterMemberJson.setMemberName(dayGroupMain.get("member_name").toString());
//				studyRegisterStudyRegisterMemberJson.setDayName(dayGroupMain.get("day_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBegin(dayGroupMain.get("begin").toString());
//				studyRegisterStudyRegisterMemberJson.setEnd(dayGroupMain.get("end").toString());
//				studyRegisterStudyRegisterMemberJson.setRoomName(dayGroupMain.get("room_name").toString());
//				studyRegisterStudyRegisterMemberJson.setBuildingName(dayGroupMain.get("building_name").toString());
//				
//		}
//		
//		
//		return studyRegisterStudyRegisterMemberJson;
//	}
//	
//	public static List<StudyRegisterStudyRegisterMemberJson> studyRegisterStudyRegisterMemberJsons(ArrayList<HashMap<String, HashMap<String, Object>>> dayGroupMain) throws CourseGroupDetException, GeSubjectGroupDetException{
//		
//		List<StudyRegisterStudyRegisterMemberJson> studyRegisterStudyRegisterMemberJsons = new ArrayList<>();
//		
//		for(int i = 0; i < dayGroupMain.size(); i++) {
//			
//			studyRegisterStudyRegisterMemberJsons.add(packStudyRegisterStudyRegisterMemberJson(dayGroupMain.get(i)));
//		}
//		
//		return studyRegisterStudyRegisterMemberJsons;
//	}
//	
//	public static List<List<StudyRegisterStudyRegisterMemberJson>> studyRegisterStudyRegisterMemberJsonsSuper(ArrayList<ArrayList<HashMap<String, Object>>> dayGroupMain) throws CourseGroupDetException, GeSubjectGroupDetException{
//		
//		List<List<StudyRegisterStudyRegisterMemberJson>> studyRegisterStudyRegisterMemberJsonsMain = new ArrayList<>();
//		List<StudyRegisterStudyRegisterMemberJson> studyRegisterStudyRegisterMemberJsons = null;
//		
//		int size = 0;
//		
//		for(int i = 0; i < dayGroupMain.size(); i++) {
//			
//			size = dayGroupMain.get(i).size();
//			
//			studyRegisterStudyRegisterMemberJsons = new ArrayList<>();
//			
//			for(int j = 0; j < size; j++) {
//				
//				
//				studyRegisterStudyRegisterMemberJsons.add(packStudyRegisterStudyRegisterMemberJsonSuper(dayGroupMain.get(i).get(j)));
//			}
//			
//			studyRegisterStudyRegisterMemberJsonsMain.add(studyRegisterStudyRegisterMemberJsons);
//			
//		}
//		
//		return studyRegisterStudyRegisterMemberJsonsMain;
//	}
//}
