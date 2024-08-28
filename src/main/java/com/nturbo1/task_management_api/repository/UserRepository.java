package com.nturbo1.task_management_api.repository;

import com.nturbo1.task_management_api.model.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
