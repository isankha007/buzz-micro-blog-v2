package com.sankha.twitter.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity  findByUsername(String userName);
	UserEntity findByEmail(String username);
	List<UserEntity> findByUsernameNot(String username);
}
