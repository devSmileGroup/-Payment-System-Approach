package com.smilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilestore.model.SpecificationKey;

@Repository
public interface SpecificationKeyRepository extends JpaRepository<SpecificationKey, Long> {

}
