package com.nturbo1.task_service.service.interfaces;

import com.nturbo1.task_service.service.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAllUserTasks(String userId);

    TaskDto addTask(TaskDto taskDto);

    TaskDto updateTask(String taskId, TaskDto taskDto);

    void deleteTask(String taskId);
}
