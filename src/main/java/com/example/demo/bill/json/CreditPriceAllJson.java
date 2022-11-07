package com.example.demo.bill.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bill.model.CreditPrice;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreditPriceAllJson {
	String creditPriceUuid;
	String CreditPrice;
	String degreeLevelName;
	
	public static CreditPriceAllJson packCreditPricePersonalAllJson(CreditPrice creditPrice) {
		CreditPriceAllJson creditPriceCourseAllJson = new CreditPriceAllJson();
		creditPriceCourseAllJson.setCreditPrice(String.format("%.2f", creditPrice.getCreditPrice()));
		creditPriceCourseAllJson.setCreditPriceUuid(creditPrice.getCreditPriceUuid());
		creditPriceCourseAllJson.setDegreeLevelName(creditPrice.getDegreeLevel().getName());
//		creditPriceCourseAllJson.setCreditPrice(creditPrice.getCreditPrice());
		return creditPriceCourseAllJson;
	}
	
	public static List<CreditPriceAllJson> creditPriceCourseAllJsons(List<CreditPrice> creditPrices){
		List<CreditPriceAllJson> creditPriceCourseAllJsons = new ArrayList<>();
		for(CreditPrice creditPrice : creditPrices) {
			creditPriceCourseAllJsons.add(packCreditPricePersonalAllJson(creditPrice));
		}
		return creditPriceCourseAllJsons;
	}
}
