package br.com.brunodemartini.workshopMongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.brunodemartini.workshopMongo.domain.Post;

@Repository
//Parâmetros: Em primeiro lugar, o tipo da classe, que no caso é User. Em segundo, o tipo
//            do ID da classe User, que é um String (id da classe user).
public interface PostRepository extends MongoRepository<Post, String>{

}
