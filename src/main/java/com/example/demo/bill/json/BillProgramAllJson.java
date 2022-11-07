package com.example.demo.bill.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bill.model.BillProgram;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BillProgramAllJson {
	String billProgramUuid;
	String billName;
	String billUid;
	String degreeUid;
	String programNmae;
	String programUid;
	String facNmae;
	String facUid;
	
	public static BillProgramAllJson packBillProgramPersonalAllJson(BillProgram billProgram) {
		BillProgramAllJson billProgramCourseAllJson = new BillProgramAllJson();
		billProgramCourseAllJson.setBillProgramUuid(billProgram.getBillProgramUuid());
		billProgramCourseAllJson.setFacUid(billProgram.getProgram().getFaculty().getFacultyUuid());
		billProgramCourseAllJson.setFacNmae(billProgram.getProgram().getFaculty().getFacultyNameEn());
		billProgramCourseAllJson.setDegreeUid(billProgram.getProgram().getDegreeLevel().getDegreeLevelUuid());
		billProgramCourseAllJson.setProgramUid(billProgram.getProgram().getProgramUuid());
		billProgramCourseAllJson.setProgramNmae(billProgram.getProgram().getProgramNameEn());
		billProgramCourseAllJson.setBillUid(billProgram.getBill().getBillUuid());
		billProgramCourseAllJson.setBillName(billProgram.getBill().getName());
		
		return billProgramCourseAllJson;
	}
	
	public static List<BillProgramAllJson> billProgramCourseAllJsons(List<BillProgram> billPrograms){
		List<BillProgramAllJson> billProgramCourseAllJsons = new ArrayList<>();
		for(BillProgram billProgram : billPrograms) {
			billProgramCourseAllJsons.add(packBillProgramPersonalAllJson(billProgram));
		}
		return billProgramCourseAllJsons;
	}
}
