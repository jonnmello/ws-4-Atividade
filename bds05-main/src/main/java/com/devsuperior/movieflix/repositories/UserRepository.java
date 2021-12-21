package com.devsuperior.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//buscar no bando de dados um usuario por email
	User findByEmail(String email);

}

