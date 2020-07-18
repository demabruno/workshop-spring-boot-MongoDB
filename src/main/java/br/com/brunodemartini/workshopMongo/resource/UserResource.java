package br.com.brunodemartini.workshopMongo.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.workshopMongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	public UserResource() {	}

	@RequestMapping(method = RequestMethod.GET) //--> Indica que será consumido por um método Get
	public ResponseEntity<List<User>> findAll(){
		List<User> listaUsuarios = new ArrayList<>();
		User maria = new User("1001", "Maria Brown", "maria@gmail.com");
		User alex = new User("1002", "Alex Green", "alex@gmail.com");
		listaUsuarios.addAll(Arrays.asList(alex, maria));
		return ResponseEntity.ok().body(listaUsuarios);
		
	}
}
