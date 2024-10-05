package com.nturbo1.task_service.controller.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {

  private String id;

  private String title;

  private String description;

  private String status;

  private LocalDate dueDate;

  private String priority;

  private String assigneeUserId;
}
