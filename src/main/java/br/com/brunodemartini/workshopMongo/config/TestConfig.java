package br.com.brunodemartini.workshopMongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brunodemartini.workshopMongo.Repository.UserRepository;
import br.com.brunodemartini.workshopMongo.domain.User;

                    //CommandLineRunner: Interface que é executada na inicializacao do projeto.
@Configuration      //Marca a classe como sendo uma classe de configuração
@Profile("test")    //Marca a classe com perfil de configuração. O "test" deve ser o conteúdo que está no atributo spring.profiles.active do arquivo application.properties
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}



}
