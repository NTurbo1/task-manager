package com.nturbo1.user_service.model.collection;

import java.util.Set;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@ToString
public class Role {

  @Id private String name;

  private Set<String> authorities;
}
