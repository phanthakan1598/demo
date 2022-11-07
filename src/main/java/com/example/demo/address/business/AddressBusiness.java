package com.example.demo.address.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.json.AddressMemberEditProfileJson;
import com.example.demo.address.json.AllAddressMemberEditProfileJson;
import com.example.demo.address.model.Address;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.payload.AddressInsertPayload;
import com.example.demo.address.payload.AddressUpdatePayload;
import com.example.demo.address.service.AddressService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;
import com.example.demo.member.model.Log;
import com.example.demo.member.model.Member;
import com.example.demo.member.service.LogService;
import com.example.demo.member.service.MemberService;
import com.example.demo.security.util.JWTAuth;

import lombok.Setter;

@Service
@Setter
public class AddressBusiness {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JWTAuth jwtAuth;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	LogService logService;
	
	public List<AllAddressMemberEditProfileJson> viewAllAddressMemberEditProfile() throws MemberException{
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUser().getMemberUuid());
		return  AllAddressMemberEditProfileJson.packMemberAdminEditProfile(addressService.getAllAddressMemberAdmin(member));
	}
	
	public AddressMemberEditProfileJson viewAllAddressMemberEditProfile(String uid) throws AddressException{
		return  AddressMemberEditProfileJson.packMemberAdminEditProfile(addressService.getAddress(uid));
	}
	
	public void insertAddressMember(AddressInsertPayload body) throws AddressException, MemberException, LogException{
		String addressDetailTh = body.getAddressDetailTh();
		String addressDetailEn = body.getAddressDetailEn();
		Tambon tambon = addressService.getTambon(body.getTambonUuid());
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUserUuid());
		
		if(addressDetailTh == null || addressDetailTh.equals("")) {
			throw AddressException.addressDetailThEmpty();
		}
		if(addressDetailEn == null || addressDetailEn.equals("")) {
			throw AddressException.addressDetailEnEmpty();
		}
		
		Address address = new Address();
		address.setAddressDetailTh(addressDetailTh);
		address.setAddressDetailEn(addressDetailEn);
		address.setTambon(tambon);
		address.setMember(member);
		
		member.setAddress(address);
		
		addressService.saveAddress(address);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("เพิ่มข้อมูลที่อยู่");
		logService.saveLog(log);
	}
	
	public void updateAddressMember(AddressUpdatePayload body) throws AddressException, LogException{
		String addressUuid = body.getAddressUuid(); 
		String addressDetailTh = body.getAddressDetailTh();
		String addressDetailEn = body.getAddressDetailEn();
		Tambon tambon = addressService.getTambon(body.getTambonUuid());
		
		if(addressUuid == null || addressUuid.equals("")) {
			throw AddressException.addressDetailThEmpty();
		}
		if(addressDetailTh == null || addressDetailTh.equals("")) {
			throw AddressException.addressDetailThEmpty();
		}
		if(addressDetailEn == null || addressDetailEn.equals("")) {
			throw AddressException.addressDetailEnEmpty();
		}
		
		Address address = addressService.getAddress(addressUuid);
		address.setAddressDetailTh(addressDetailTh);
		address.setAddressDetailEn(addressDetailEn);
		address.setTambon(tambon);
		
		addressService.saveAddress(address);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลที่อยู่");
		logService.saveLog(log);
	}
	
	public void chooseAddressMemberAdmin(String uid) throws AddressException, MemberException, LogException{
		Address address = addressService.getAddress(uid);
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUserUuid());
		member.setAddress(address);
		memberService.saveMember(member);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("แก้ไขข้อมูลที่อยู่");
		logService.saveLog(log);
	}
	
	public void delAddressMember(String uid)throws AddressException, MemberException, LogException {
		if(uid == null || uid.equals("")) {
			throw AddressException.addressDetailUidEmpty();
		}
		
		Member member = memberService.findMemberByUuid(jwtAuth.getCurrentUserUuid());
		String memberAddressUsed = member.getAddress().getAddressUuid();
		
		if(uid.equals(memberAddressUsed)) {
			throw MemberException.memberAddressUsed();
		}
		
		Address address = addressService.getAddress(uid);
		addressService.delAddress(address);
		
		Log log =  new Log();
		log.setMemberName(jwtAuth.getCurrentUser().getMemberUsername());
		log.setLogEvent("ลบข้อมูลที่อยู่");
		logService.saveLog(log);
	}
}
