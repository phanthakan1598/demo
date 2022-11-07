package com.example.demo.address.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.address.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{
	Optional<Region> findOneByRegionUuid(String RegionUuid);
	<T>Optional<T> findOneByRegionUuid(String RegionUuid, Class<T> type);
}
