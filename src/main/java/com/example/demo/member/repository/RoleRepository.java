package com.example.demo.member.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findOneByRoleUuid(String RoleUuid);
//	Optional<Role>findRoleBetween(Role role1,Role role2);
}
