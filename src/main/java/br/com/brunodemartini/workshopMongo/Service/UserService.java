package br.com.brunodemartini.workshopMongo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunodemartini.workshopMongo.Repository.UserRepository;
import br.com.brunodemartini.workshopMongo.Service.Exception.ObjectNotFoundException;
import br.com.brunodemartini.workshopMongo.domain.User;
import br.com.brunodemartini.workshopMongo.dto.UserDto;

@Service
public class UserService {

	public UserService() {}
	
	@Autowired //Instancia de forma automatica a clase Repository
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User newObj = findById(user.getId());
		updateData(newObj, user);
		return userRepository.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
