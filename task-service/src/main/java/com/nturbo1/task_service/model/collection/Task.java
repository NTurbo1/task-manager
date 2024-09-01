package com.nturbo1.task_service.model.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private String status;

    @Field
    private LocalDate dueDate;

    @Field
    private String priority;
}
