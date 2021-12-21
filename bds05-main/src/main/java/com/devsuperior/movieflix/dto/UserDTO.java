package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.User;

public class UserDTO implements Serializable{
		private static final long serialVersionUID = 1L;
		
	private long id;
	private String name;
	private String email;
	
	public UserDTO() {
		
	}

	public UserDTO(long id, String name, String email) {
		
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
public UserDTO(User entity) { //facilitar conversao de entidade para dto
	id = entity.getId();
	name = entity.getName();
	email = entity.getEmail();
	}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

}
