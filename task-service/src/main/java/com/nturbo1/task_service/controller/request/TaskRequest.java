package com.nturbo1.task_service.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {

	private String title;

	private String description;

	private String status;

	private LocalDate dueDate;

	private String priority;

	private String assigneeUserId;
}
