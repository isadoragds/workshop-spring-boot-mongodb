package com.isadora.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isadora.workshopmongo.domain.User;
import com.isadora.workshopmongo.repository.UserRepository;
import com.isadora.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	//o servico se relaciona com o repositorio
	@Autowired //instancia automaticamente a dependencia
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}
	
	//retorna um usuario por id recebendo uma string como argumento
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
