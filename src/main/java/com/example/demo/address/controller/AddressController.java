package com.example.demo.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.address.business.AddressBusiness;
import com.example.demo.address.exception.AddressException;
import com.example.demo.address.json.AddressMemberEditProfileJson;
import com.example.demo.address.json.AllAddressMemberEditProfileJson;
import com.example.demo.address.json.AmphurJson;
import com.example.demo.address.json.ProvinceJson;
import com.example.demo.address.json.RegionJson;
import com.example.demo.address.json.TambonJson;
import com.example.demo.address.payload.AddressInsertPayload;
import com.example.demo.address.payload.AddressUpdatePayload;
import com.example.demo.address.service.AddressService;
import com.example.demo.member.exception.LogException;
import com.example.demo.member.exception.MemberException;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressBusiness addressBusiness;
	
//	@GetMapping(value = "/region")
//	public List<Region> getAllRegion(){
//		return addressService.getAllRegion();
//	}
	
	@GetMapping(value = "/region")
	public ResponseEntity<List<RegionJson>> RegionList(){
		return ResponseEntity.ok(addressService.getAllRegions());
	}
	
	@GetMapping(value = "/province/{uuid}")
	public ResponseEntity<List<ProvinceJson>> ProvinceList(@PathVariable String uuid){
		return ResponseEntity.ok(addressService.getAllProvinces(uuid));
	}
	
	@GetMapping(value = "/amphur/{uuid}")
	public ResponseEntity<List<AmphurJson>> AmphurList(@PathVariable String uuid){
		return ResponseEntity.ok(addressService.getAllAmphur(uuid));
	}
	
	@GetMapping(value = "/tambon/{uuid}")
	public ResponseEntity<List<TambonJson>> TambonList(@PathVariable String uuid){
		return ResponseEntity.ok(addressService.getAllTambon(uuid));
	}
	
	@GetMapping(value = "/viewAllAddressMember")
	public ResponseEntity<List<AllAddressMemberEditProfileJson>> viewAllAddressEditProfileMemberAdmin() throws MemberException{
		return ResponseEntity.ok(addressBusiness.viewAllAddressMemberEditProfile());
	}
	
	@GetMapping(value = "/viewAddressMember/{uuid}")
	public ResponseEntity<AddressMemberEditProfileJson> viewAddressEditProfileMemberAdmin(@PathVariable String uuid) throws AddressException{
		return ResponseEntity.ok(addressBusiness.viewAllAddressMemberEditProfile(uuid));
	}
	
	@PostMapping(value = "/insertAddressMemberAdmin")
	public ResponseEntity<Void> insertAddressMemberAdmin(@RequestBody AddressInsertPayload body) throws MemberException, AddressException, LogException{
		addressBusiness.insertAddressMember(body);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateAddressMemberAdmin")
	public ResponseEntity<Void> updateAddressMemberAdmin(@RequestBody AddressUpdatePayload body) throws AddressException, LogException{
		addressBusiness.updateAddressMember(body);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping(value = "/chooseAddressMemberAdmin/{uuid}")
	public ResponseEntity<Void> chooseAddressMemberAdmin(@PathVariable String uuid) throws AddressException, MemberException, LogException{
		addressBusiness.chooseAddressMemberAdmin(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/delAddressMember/{uuid}")
	public ResponseEntity<Void> delAddressMember(@PathVariable String uuid) throws AddressException, MemberException, LogException{
		addressBusiness.delAddressMember(uuid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	
	
	
}
