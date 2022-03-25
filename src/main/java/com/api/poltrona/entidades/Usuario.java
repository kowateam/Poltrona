package com.api.poltrona.entidades;

import org.springframework.data.annotation.Id;

import com.api.poltrona.enums.Roles;

public class Usuario {
	@Id
	private String id;
	private String mail;
	private String password;
	private Roles role;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	
}
