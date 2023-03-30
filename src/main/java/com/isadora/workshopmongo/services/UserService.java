package com.isadora.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isadora.workshopmongo.domain.User;
import com.isadora.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	//o metodo ta nessa classe e nao na classe do dto, pois essa classe tem a instanciacao do repositorio. caso no futuro, precise ter acesso ao banco
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
