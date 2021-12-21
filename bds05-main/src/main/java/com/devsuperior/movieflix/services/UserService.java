package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class); // usar um objeto de logger ele vai
																				// imprimir mensagens no console
																				// obdecendo padrao "quem é erro etc"

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) { // busca por id, passa o id

		authService.validationSelfOrAdmin(id);

		Optional<User> obj = repository.findById(id); // usa o repository para buscar e retornar optional
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));// para obter a entidade
																								// no optional chama
																								// obj.orElseThrow(()
																								// lançando exerção de
																								// nao encontrado cada
																								// venha vazio
		return new UserDTO(entity);// converte ela para dto
	}

	@Override // implementar uma busca por email simples
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username); // esse username ta igual email
		if (user == null) { // se o usuario for nullo ele lança exerção
			logger.error("User not Found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User Found: " + username);
		return user;

	}

	@Transactional(readOnly = true)
	public UserDTO getProfile() {
		return new UserDTO(authService.authenticated());
	}

}
