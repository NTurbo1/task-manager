package com.nturbo1.task_management_api.controller.request;

import java.time.LocalDate;

public class TaskRequest {

    private String title;

    private String description;

    private String status;

    private LocalDate dueDate;

    private String priority;
}
