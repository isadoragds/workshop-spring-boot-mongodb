package com.isadora.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.isadora.workshopmongo.domain.User;
import com.isadora.workshopmongo.dto.UserDTO;
import com.isadora.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	//o controlador rest acessa o servico e o servico acessa o repositorio
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) //ou @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET) //ou @GetMapping
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { //o pathvariable é pro id ser o mesmo que o recebido na url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST) //ou @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) { //para a requisicao aceitar o obj utiliza-se o requestbody
		User obj = service.fromDTO(objDto); //convert o dto para user
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //boa pratica - url do novo recurso criado
		return ResponseEntity.created(uri).build(); //created retorna codigo 201 - quando cria novo recurso
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Void> delete(@PathVariable String id) { //o pathvariable é pro id ser o mesmo que o recebido na url
		service.delete(id);
		return ResponseEntity.noContent().build(); //204 - no content 
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) { 
		User obj = service.fromDTO(objDto); //convert o dto para user
		obj.setId(id); //garante que o obj tera o id da requisicao
		obj = service.update(obj);
		return ResponseEntity.noContent().build(); //204 - no content 
	}
	
	
}
