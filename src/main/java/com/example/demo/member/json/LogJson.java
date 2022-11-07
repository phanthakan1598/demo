package com.example.demo.member.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.member.model.Log;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LogJson {
	String memberName;
	String logEvent;
	String logTime;
	

	public static LogJson packLogJson(Log log) {
		LogJson logJson = new LogJson();
		logJson.setMemberName(log.getMemberName());
		logJson.setLogEvent(log.getLogEvent());
		logJson.setLogTime(log.getCreateAt().getDayOfMonth()+"-"
							+log.getCreateAt().getMonthValue()+"-"
							+Integer.toString(log.getCreateAt().getYear() + 543 * 2)+" เวลา "
							+log.getCreateAt().getHour()+":"+log.getCreateAt().getMinute() +" น.");
		return logJson;
	}
	
	public static List<LogJson> logJsons(List<Log> logs){
		List<LogJson> logJsons = new ArrayList<>();
		for(Log log : logs) {
			logJsons.add(packLogJson(log));
		}
		return logJsons;
	}

}