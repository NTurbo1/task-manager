package com.nturbo1.user_service.database.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
@ChangeUnit(id = "create-users-collection", order = "001", author = "nturbo1")
public class CreateUsersCollectionChange {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createUsersCollection() {
        mongoTemplate.createCollection("users");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("users");
    }
}
