package br.com.brunodemartini.workshopMongo.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.workshopMongo.Service.UserService;
import br.com.brunodemartini.workshopMongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired //Reconhecer a injeção de dependência: Como se instanciasse a classe Q.
	private UserService userService;

	public UserResource() {	}

	@RequestMapping(method = RequestMethod.GET) //--> Indica que será consumido por um método GET
	public ResponseEntity<List<User>> findAll(){
		List<User> listaUsuarios = userService.findAll();
		return ResponseEntity.ok().body(listaUsuarios);
	}
}
