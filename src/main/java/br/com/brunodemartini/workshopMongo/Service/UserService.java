package br.com.brunodemartini.workshopMongo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.workshopMongo.Repository.UserRepository;
import br.com.brunodemartini.workshopMongo.domain.User;

@Service
public class UserService {

	public UserService() {}
	
	@Autowired //Instancia de forma automatica a clase Repository
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

}
