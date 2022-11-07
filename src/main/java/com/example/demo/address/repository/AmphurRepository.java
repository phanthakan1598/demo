package com.example.demo.address.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.address.model.Amphur;
import com.example.demo.address.model.Province;

@Repository
public interface AmphurRepository extends JpaRepository<Amphur, Long>{
	Optional<Amphur> findOneByAmphurUuid(String AmphurUuid);
	List<Amphur> findByProvince(Province province);
	<T>Optional<T> findOneByAmphurUuid(String AmphurUuid, Class<T> type);
}
