package com.nturbo1.task_service.service.interfaces;

import com.nturbo1.task_service.controller.request.TaskRequest;
import com.nturbo1.task_service.service.dto.TaskDto;

import java.util.List;

public interface TaskService {

    /**
     * Returns all the tasks of the **AUTHENTICATED** user.
     *
     * @return A list of tasks of an authenticated user.
     */
    List<TaskDto> getAllTasks();

    /**
     * Creates a task with the specified data in the request for the **AUTHENTICATED** user.
     *
     * @param taskRequest Task request.
     * @return Dto of a newly created task.
     */
    TaskDto addTask(TaskRequest taskRequest);

    /**
     * Updates a task with the specified data in the request for the **AUTHENTICATED** user.
     *
     * @param taskRequest Task request.
     * @return Dto of the updated task.
     *
     * @throws com.nturbo1.task_service.exception.ResourceNotFoundException, if the task doesn't exist.
     */
    TaskDto updateTask(TaskRequest taskRequest);

    /**
     * Deletes a task of the **AUTHENTICATED** user.
     *
     * @param taskId Task id.
     * @throws com.nturbo1.task_service.exception.ResourceNotFoundException, if the task doesn't exist.
     */
    void deleteTask(String taskId);
}
