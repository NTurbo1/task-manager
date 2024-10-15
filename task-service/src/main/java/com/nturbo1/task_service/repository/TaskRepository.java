package com.nturbo1.task_service.repository;

import com.nturbo1.task_service.model.collection.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findAllByAssigneeUserId(String assigneeUserId);
}
