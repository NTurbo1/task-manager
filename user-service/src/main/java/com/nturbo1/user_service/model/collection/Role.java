package com.nturbo1.user_service.model.collection;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "roles")
@Data
@ToString
public class Role {

    @Id
    private String name;

    private Set<String> authorities;
}
