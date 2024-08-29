package com.nturbo1.task_management_api.repository;

import com.nturbo1.task_management_api.model.collection.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
