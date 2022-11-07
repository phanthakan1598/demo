package com.example.demo.address.json;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.address.model.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AllAddressMemberEditProfileJson {
	String addressUid;
	String addressTh;
	String addressEn;
	
	public static AllAddressMemberEditProfileJson packMemberAdminEditProfile(Address address) {
		AllAddressMemberEditProfileJson memberJson = new AllAddressMemberEditProfileJson();
		memberJson.setAddressUid(address.getAddressUuid());
		memberJson.setAddressTh(address.getAddressDetailTh()+
								" ต."+address.getTambon().getTambonNameTh()+
								" อ."+address.getTambon().getAmphur().getAmphurNameTh()+
								" จ."+address.getTambon().getAmphur().getProvince().getProvinceNameTh()+
								" "+address.getTambon().getTambonZipCode());
		memberJson.setAddressEn(address.getAddressDetailEn()+
				" Sub-district: "+address.getTambon().getTambonNameEn()+
				" District: "+address.getTambon().getAmphur().getAmphurNameEn()+
				" Province: "+address.getTambon().getAmphur().getProvince().getProvinceNameEn()+
				" "+address.getTambon().getTambonZipCode());
		return memberJson;
	}
	
	public static List<AllAddressMemberEditProfileJson> packMemberAdminEditProfile(List<Address> addresses){
		List<AllAddressMemberEditProfileJson> addressMemberAdminEditProfiles = new ArrayList<>();
		for(Address address : addresses) {
			addressMemberAdminEditProfiles.add(packMemberAdminEditProfile(address));
		}
		return addressMemberAdminEditProfiles;
	}
}
