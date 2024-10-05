package com.nturbo1.task_service.service.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

  private String id;

  private String title;

  private String description;

  private String status;

  private LocalDate dueDate;

  private String priority;

  private String assigneeUserId;
}
