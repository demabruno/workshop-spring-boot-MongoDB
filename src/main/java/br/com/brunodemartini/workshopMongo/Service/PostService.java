package br.com.brunodemartini.workshopMongo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.workshopMongo.Repository.PostRepository;
import br.com.brunodemartini.workshopMongo.Service.Exception.ObjectNotFoundException;
import br.com.brunodemartini.workshopMongo.domain.Post;

@Service
public class PostService {

	public PostService() {}
	
	@Autowired //Instancia de forma automatica a clase Repository
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		return optionalPost.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
