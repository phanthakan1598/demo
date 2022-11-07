package com.example.demo.address.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.address.model.Province;
import com.example.demo.address.model.Region;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{
	Optional<Province> findOneByProvinceUuid(String ProvinceUuid);
	List<Province> findByRegion(Region region);
	<T>Optional<T> findOneByProvinceUuid(String ProvinceUuid, Class<T> type);
}
