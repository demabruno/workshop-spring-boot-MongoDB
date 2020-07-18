package br.com.brunodemartini.workshopMongo.resource;

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

import br.com.brunodemartini.workshopMongo.Service.UserService;
import br.com.brunodemartini.workshopMongo.domain.Post;
import br.com.brunodemartini.workshopMongo.domain.User;
import br.com.brunodemartini.workshopMongo.dto.UserDto;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired //Reconhecer a injeção de dependência: Como se instanciasse a classe Q.
	private UserService userService;

	public UserResource() {	}

	@RequestMapping(method = RequestMethod.GET) //--> Indica que será consumido por um método GET
	public ResponseEntity<List<UserDto>> findAll(){
		List<User> listaUsuarios = userService.findAll();
		//Expressão lambda que  varre a lista de Usuarios e converte para uma lista de usuários DTO
		List<UserDto> listaUsuariosDto = listaUsuarios.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaUsuariosDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) //--> Indica que será consumido por um método GET
	//A tag @RequestMapping tem o mesmo valor da tag @Getmapping
	public ResponseEntity<UserDto> findById(@PathVariable String id){
		User user = userService.findById(id);
		UserDto userDto = new UserDto(user);
		
		return ResponseEntity.ok().body(userDto);
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto){
		User user = userService.insert(userService.fromDto(userDto));
		//Retornando a URI do objeto que foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		//created: Retorna o código 201, que é o código HTTP quando tu cria um novo recurso.
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		userService.delete(id); 
		
		//noContent(): Retorna o código 204, de deleção
		return ResponseEntity.noContent().build(); 
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDto userDto, @PathVariable String id){
		User obj = userService.fromDto(userDto);
		obj.setId(id);
		obj= userService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User user = userService.findById(id);
			
		return ResponseEntity.ok().body(user.getListaPosts());
	}
}
