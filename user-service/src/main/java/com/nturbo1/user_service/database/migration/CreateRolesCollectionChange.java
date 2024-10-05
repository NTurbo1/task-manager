package com.nturbo1.user_service.database.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
@ChangeUnit(id = "create-roles-collection", order = "002", author = "nturbo1")
public class CreateRolesCollectionChange {

  private final MongoTemplate mongoTemplate;

  @Execution
  public void createRolesCollection() {
    mongoTemplate.createCollection("roles");
  }

  @RollbackExecution
  public void rollback() {
    mongoTemplate.dropCollection("roles");
  }
}
