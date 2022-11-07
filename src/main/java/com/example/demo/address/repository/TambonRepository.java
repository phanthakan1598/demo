package com.example.demo.address.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.address.model.Amphur;
import com.example.demo.address.model.Tambon;

public interface TambonRepository extends JpaRepository<Tambon, Long>{
	Optional<Tambon> findOneByTambonUuid(String TambonUuid);
	List<Tambon> findByAmphur(Amphur amphur);
	<T>Optional<T> findOneByTambonUuid(String TambonUuid, Class<T> type);
}
