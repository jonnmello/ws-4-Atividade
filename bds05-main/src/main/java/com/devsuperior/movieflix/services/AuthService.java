package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.exceptions.ForbiddenException;
import com.devsuperior.movieflix.exceptions.UnauthorizedException;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)//informando que essa operação somente leitura, nao trava o banco
	public User authenticated() {
		try {//caso acha um erro, de nao encontrar usuario ou nao logado
		String username = SecurityContextHolder.getContext().getAuthentication().getName();//essa chamada extatica pega o usuario que ja foi reconhecido pelo spring security o nome
		return userRepository.findByEmail(username);
	}
		catch(Exception e) {
			
			throw new UnauthorizedException("Invalid User");
		}
	}
	
	public void validationSelfOrAdmin(Long userId) { //validar se o dado usuario se for o proprio usuario logado ou admin passa ou lança execeção
		User user  = authenticated();      //peguei usuario autenticado
		if(!user.getId().equals(userId)&& !user.hasHole("ROLE_ADMIN")) { //se o id do usuario logado for diferente do userId e && não for admin
			
			throw new ForbiddenException("Access denied ");
		}
		
	}

}
