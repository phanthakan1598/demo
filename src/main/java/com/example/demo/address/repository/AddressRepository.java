package com.example.demo.address.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.address.model.Address;
import com.example.demo.member.model.Member;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	Optional<Address> findOneByAddressUuid(String AddressUuid);
	List<Address> findByMemberOrderByUpdatedAtDesc(Member member);
}
