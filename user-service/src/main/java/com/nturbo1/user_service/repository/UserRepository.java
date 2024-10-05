package com.nturbo1.user_service.repository;

import com.nturbo1.user_service.model.collection.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);
}
