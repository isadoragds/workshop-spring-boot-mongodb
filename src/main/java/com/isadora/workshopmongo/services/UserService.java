package com.isadora.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isadora.workshopmongo.domain.User;
import com.isadora.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	//o servico se relaciona com o repositorio
	@Autowired //instancia automaticamente a dependencia
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

}
