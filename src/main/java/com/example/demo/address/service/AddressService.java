package com.example.demo.address.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.address.exception.AddressException;
import com.example.demo.address.json.AmphurJson;
import com.example.demo.address.json.ProvinceJson;
import com.example.demo.address.json.RegionJson;
import com.example.demo.address.json.TambonJson;
import com.example.demo.address.model.Address;
import com.example.demo.address.model.Amphur;
import com.example.demo.address.model.Province;
import com.example.demo.address.model.Region;
import com.example.demo.address.model.Tambon;
import com.example.demo.address.repository.AddressRepository;
import com.example.demo.address.repository.AmphurRepository;
import com.example.demo.address.repository.ProvinceRepository;
import com.example.demo.address.repository.RegionRepository;
import com.example.demo.address.repository.TambonRepository;
import com.example.demo.member.model.Member;

import lombok.Setter;

@Service
@Setter
public class AddressService {
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	ProvinceRepository provinceRepository;
	
	@Autowired
	AmphurRepository amphurRepository;
	
	@Autowired
	TambonRepository tambonRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	
//	public List<Region>	 getAllRegion(){
//		return repository.findAll();
//	}
	
	public List<Address> getAllAddressMemberAdmin(Member member){
		return addressRepository.findByMemberOrderByUpdatedAtDesc(member);
	}
	
	
	public List<RegionJson>	getAllRegions(){
		return RegionJson.packRegionJson(regionRepository.findAll());
	}
	
	public List<ProvinceJson> getAllProvinces(String RegionUuid){
		Region region = regionRepository.findOneByRegionUuid(RegionUuid)
		.orElseThrow(() -> new UsernameNotFoundException(String.format("Region: %s, not found", RegionUuid)));
		return ProvinceJson.packProvinceJson(provinceRepository.findByRegion(region));
	}
	
	public List<AmphurJson> getAllAmphur(String ProvinceUuid){
		Province province = provinceRepository.findOneByProvinceUuid(ProvinceUuid)
		.orElseThrow(() -> new UsernameNotFoundException(String.format("Province: %s, not found", ProvinceUuid)));
		return AmphurJson.packAmphurJson(amphurRepository.findByProvince(province));
	}
	
	public List<TambonJson> getAllTambon(String AmphurUuid){
		Amphur amphur = amphurRepository.findOneByAmphurUuid(AmphurUuid)
		.orElseThrow(() -> new UsernameNotFoundException(String.format("Amphur: %s, not found", AmphurUuid)));
		return TambonJson.packTambonJson(tambonRepository.findByAmphur(amphur));
	}
	
	public Tambon getTambon(String TambonUuid) throws AddressException{
		return tambonRepository.findOneByTambonUuid(TambonUuid).orElseThrow(AddressException::tambonNotFound);
	}
	
	public Address getAddress(String AddressrUuid) throws AddressException{
		return addressRepository.findOneByAddressUuid(AddressrUuid).orElseThrow(AddressException::addressNotFound);
	}
	
	public Address saveAddress(Address address) throws AddressException {
    	if(Objects.isNull(address)) {
    		throw AddressException.addressIsEmptyOrNull();
    	}
		return addressRepository.save(address);
	}
	
	public void delAddress(Address address) throws AddressException {
    	if(Objects.isNull(address)) {
    		throw AddressException.addressIsEmptyOrNull();
    	}
    	addressRepository.delete(address);
	}
}
