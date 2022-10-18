package com.finzlyloan.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity
public class UserSession {
	
	@Id
	private String email;
	private String token;
	private LocalDateTime date;
	public UserSession(String token, String email, LocalDateTime date) {
		super();
		this.token = token;
		this.email = email;
		this.date = date;
	}
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}
