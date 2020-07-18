package br.com.brunodemartini.workshopMongo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunodemartini.workshopMongo.Service.PostService;
import br.com.brunodemartini.workshopMongo.domain.Post;
import br.com.brunodemartini.workshopMongo.dto.UserDto;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired //Reconhecer a injeção de dependência: Como se instanciasse a classe Q.
	private PostService postService;

	public PostResource() {	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) //--> Indica que será consumido por um método GET
	//A tag @RequestMapping tem o mesmo valor da tag @Getmapping
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = postService.findById(id);
				
		return ResponseEntity.ok().body(post);
	}
}
