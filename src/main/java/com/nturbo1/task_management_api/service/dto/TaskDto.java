package com.nturbo1.task_management_api.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {

    private String id;

    private String title;

    private String description;

    private String status;

    private LocalDate dueDate;

    private String priority;
}
