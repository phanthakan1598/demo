//package com.example.demo.building.service;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.building.json.BuildingJson;
//import com.example.demo.building.json.BuildingTypeJson;
//import com.example.demo.building.json.RoomJson;
//import com.example.demo.building.json.RoomTypeJson;
//import com.example.demo.building.model.Building;
//import com.example.demo.building.model.BuildingType;
//import com.example.demo.building.model.Room;
//import com.example.demo.building.model.RoomType;
//import com.example.demo.building.repository.BuildingRepository;
//import com.example.demo.building.repository.BuildingTypeRepository;
//import com.example.demo.building.repository.RoomRepository;
//import com.example.demo.building.repository.RoomTypeRepository;
//import com.example.demo.member.exception.MemberException;
//import com.example.demo.member.service.MemberService;
//import com.example.demo.security.util.JWTAuth;
//import com.example.demo.university.service.UniversityService;
//
//import lombok.Setter;
//
//@Service
//@Setter
//public class BuildingService {
//	
//	@Autowired
//	BuildingTypeRepository buildingTypeRepository;
//	
//	@Autowired
//	UniversityService universityService;
//	
//	@Autowired
//	JWTAuth jwtAuth;
//	
//	@Autowired
//	MemberService memberService;
//	
//	@Autowired
//	RoomTypeRepository roomTypeRepository;
//	
//	@Autowired
//	BuildingRepository buildingRepository;
//	
//	@Autowired
//	RoomRepository roomRepository;
//	
//	///////////////////////////////////////// BuildingType /////////////////////////////////////////////////////
//	public BuildingType saveBuildingType(BuildingType buildingType) throws MemberException {
//    	if(Objects.isNull(buildingType)) {
//    		throw MemberException.userIsEmptyOrNull();
//    	}
//		return buildingTypeRepository.save(buildingType);
//	}
//	
////	public List<BuildingTypeJson> getBuildingType() throws MemberException{
////		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
////		return BuildingTypeJson.packBuildingTypeJson(buildingTypeRepository.findByUniversity(university));
////	}
//	public BuildingTypeJson getBuildingTypeUuid(String uuid)throws MemberException{
//		return BuildingTypeJson.packBuildingTypeJson(findByBuildingTypeUuid(uuid));
//	}
//	
//	public BuildingType findByBuildingTypeUuid(String uuid) throws MemberException {
//        return buildingTypeRepository.findOneByBuildingTypeUuid(uuid).orElseThrow(MemberException::userNotFound);
//    }
//	
//	public void deleteBuildingType(String uuid) throws MemberException {
//		BuildingType buildingType = findByBuildingTypeUuid(uuid);
//		buildingTypeRepository.delete(buildingType);
//	}
//	///////////////////////////////////////// RoomType //////////////////////////////////////////////////////////
////	public List<RoomTypeJson> getRoomType()throws MemberException{
////		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
////		return RoomTypeJson.packRoomTypeJson(roomTypeRepository.findByUniversity(university));
////	}
//	
//	public RoomTypeJson getRoomTypeUuid(String uuid)throws MemberException{
//		return RoomTypeJson.packRoomTypeJson(findByRoomTypeUuid(uuid));
//	}
//	
//	public RoomType saveRoomType(RoomType roomType) throws MemberException {
//    	if(Objects.isNull(roomType)) {
//    		throw MemberException.userIsEmptyOrNull();
//    	}
//		return roomTypeRepository.save(roomType);
//	}
//	
//	public RoomType findByRoomTypeUuid(String uuid) throws MemberException {
//        return roomTypeRepository.findOneByRoomTypeUuid(uuid).orElseThrow(MemberException::userNotFound);
//    }
//	
//	public void deleteRoomType(String uuid) throws MemberException {
//		RoomType roomType = findByRoomTypeUuid(uuid);
//		roomTypeRepository.delete(roomType);
//	}
//	///////////////////////////////////////// Building //////////////////////////////////////////////////////////
//	public Building saveBuilding(Building building) throws MemberException {
//    	if(Objects.isNull(building)) {
//    		throw MemberException.userIsEmptyOrNull();
//    	}
//		return buildingRepository.save(building);
//	}
//	
//	public BuildingJson getBuildingUuid(String uuid)throws MemberException{
//		return BuildingJson.packBuildingJson(findByBuildingUuid(uuid));
//	}
//	
////	public List<BuildingJson> getBuilding()throws MemberException{
////		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
////		return BuildingJson.packBuildingJson(buildingRepository.findBybuildingTypeUniversity(university));
////	}
//	
//	public Building findByBuildingUuid(String uuid) throws MemberException {
//        return buildingRepository.findOneByBuildingUuid(uuid).orElseThrow(MemberException::userNotFound);
//    }
//	
//	public void deleteBuilding(String uuid) throws MemberException {
//		Building building = findByBuildingUuid(uuid);
//		buildingRepository.delete(building);
//	}
//	///////////////////////////////////////////// Room //////////////////////////////////////////////////////////
//	public Room saveRoom(Room room) throws MemberException {
//    	if(Objects.isNull(room)) {
//    		throw MemberException.userIsEmptyOrNull();
//    	}
//		return roomRepository.save(room);
//	}
//	
//	public RoomJson getRoomUuid(String uuid)throws MemberException{
//		return RoomJson.packRoomJson(findByRoomUuid(uuid));
//	}
//	
//	public Room findByRoomUuid(String uuid) throws MemberException {
//        return roomRepository.findOneByRoomUuid(uuid).orElseThrow(MemberException::userNotFound);
//    }
//	
////	public List<RoomJson> getRoom(String uuid)throws MemberException{
////		University university = universityService.findUniversityByUuid(jwtAuth.getCurrentUser().getUniversity().getUniversityUuid());
////		Building building = findByBuildingUuid(uuid);
////		return RoomJson.packRoomJson(roomRepository.findByRoomTypeUniversityAndBuilding(university,building));
////	}
//	public void deleteRoom(String uuid) throws MemberException {
//		Room room = findByRoomUuid(uuid);
//		roomRepository.delete(room);
//	}
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
////		Member user = findMemberByUuid(uuid);
////		memberRepository.deleteById(user.getMemberId());
////		return "Member with Uuid :"+uuid+" is deleted";
////	}
//	
//	
//}
