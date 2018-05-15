package com.smilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilestore.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByName(String name);
}
