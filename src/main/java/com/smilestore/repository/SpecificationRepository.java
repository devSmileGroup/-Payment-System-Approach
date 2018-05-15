package com.smilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilestore.model.Specification;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

}
