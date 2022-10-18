package com.finzlyloan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finzlyloan.entity.UserSession;

public interface UserSessionDao extends JpaRepository<UserSession, String> {

	UserSession findByToken(String token);
	

}
