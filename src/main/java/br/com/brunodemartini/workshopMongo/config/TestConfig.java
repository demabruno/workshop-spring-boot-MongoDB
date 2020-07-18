package br.com.brunodemartini.workshopMongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brunodemartini.workshopMongo.Repository.PostRepository;
import br.com.brunodemartini.workshopMongo.Repository.UserRepository;
import br.com.brunodemartini.workshopMongo.domain.Post;
import br.com.brunodemartini.workshopMongo.domain.User;
import br.com.brunodemartini.workshopMongo.dto.AuthorDto;

                    //CommandLineRunner: Interface que é executada na inicializacao do projeto.
@Configuration      //Marca a classe como sendo uma classe de configuração
@Profile("test")    //Marca a classe com perfil de configuração. O "test" deve ser o conteúdo que está no atributo spring.profiles.active do arquivo application.properties
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("18/03/2018"), "Partiu Viagem", "Vou viajar para SP!", new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDto(maria));
	
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getListaPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}



}
