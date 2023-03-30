package com.isadora.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.isadora.workshopmongo.domain.User;

@Repository //fazer operacoes basicas com o usuario -> save, delete etc
public interface UserRepository extends MongoRepository<User, String> { //entedidade e tipo de id
	
	
		
}
