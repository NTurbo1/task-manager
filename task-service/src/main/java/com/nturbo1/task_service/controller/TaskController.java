package com.nturbo1.task_service.controller;

import com.nturbo1.task_service.controller.request.TaskRequest;
import com.nturbo1.task_service.controller.response.TaskResponse;
import com.nturbo1.task_service.mapper.TaskMapper;
import com.nturbo1.task_service.service.dto.TaskDto;
import com.nturbo1.task_service.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/tasks/user/{userId}")
    public ResponseEntity<List<TaskResponse>> getAllUserTasks(@PathVariable String userId) {
        List<TaskResponse> allUserTasks = taskMapper.toResponseList(
                taskService.getAllUserTasks(userId)
        );

        return ResponseEntity.ok(allUserTasks);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        TaskDto createdTask = taskService.addTask(taskMapper.toDto(request));

        return ResponseEntity.ok(taskMapper.toResponse(createdTask));
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String taskId, @RequestBody TaskRequest request) {
        TaskDto updatedTask = taskService.updateTask(taskId, taskMapper.toDto(request));

        return ResponseEntity.ok(taskMapper.toResponse(updatedTask));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build();
    }
}
