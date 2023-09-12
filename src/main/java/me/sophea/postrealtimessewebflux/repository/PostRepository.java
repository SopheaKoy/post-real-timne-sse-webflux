package me.sophea.postrealtimessewebflux.repository;

import me.sophea.postrealtimessewebflux.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findPostById(String id);


}
