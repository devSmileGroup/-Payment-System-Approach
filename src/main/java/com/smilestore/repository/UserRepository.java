package com.smilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilestore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByNickName(String nickName);
	
}
