package com.example.demo.address.json;

import com.example.demo.address.model.Address;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressMemberEditProfileJson {
	String addressUid;
	String addressTh;
	String addressEn;
	String tambonUuid;
	int zipCode;
	String amphurUuid;
	String provinceUuid;
	String regionUuid;
	
	public static AddressMemberEditProfileJson packMemberAdminEditProfile(Address address) {
		AddressMemberEditProfileJson memberJson = new AddressMemberEditProfileJson();
		memberJson.setAddressUid(address.getAddressUuid());
		memberJson.setTambonUuid(address.getTambon().getTambonUuid());
		memberJson.setZipCode(address.getTambon().getTambonZipCode());
		memberJson.setAmphurUuid(address.getTambon().getAmphur().getAmphurUuid());
		memberJson.setProvinceUuid(address.getTambon().getAmphur().getProvince().getProvinceUuid());
		memberJson.setRegionUuid(address.getTambon().getAmphur().getProvince().getRegion().getRegionUuid());
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
}
