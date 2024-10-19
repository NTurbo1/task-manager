package com.nturbo1.task_service.service.implementations;

import com.nturbo1.task_service.exception.ResourceNotFoundException;
import com.nturbo1.task_service.exception.util.ExceptionMessage;
import com.nturbo1.task_service.mapper.TaskMapper;
import com.nturbo1.task_service.model.collection.Task;
import com.nturbo1.task_service.repository.TaskRepository;
import com.nturbo1.task_service.service.dto.TaskDto;
import com.nturbo1.task_service.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	@Override
	public List<TaskDto> getAllUserTasks(String userId) {
		List<Task> userTasks = taskRepository.findAllByAssigneeUserId(userId);

		return taskMapper.toDtoList(userTasks);
	}

	@Override
	public TaskDto addTask(TaskDto taskDto) {
		Task savedTask = taskRepository.save(taskMapper.toModel(taskDto));

		return taskMapper.toDto(savedTask);
	}

	@Override
	public TaskDto updateTask(String taskId, TaskDto taskDto) {
		Task existingTask = findByIdOrElseThrow(taskId);
		Task updatedTask = taskMapper.updateFromDto(taskDto, existingTask);
		Task savedUpdatedTask = taskRepository.save(updatedTask);

		return taskMapper.toDto(savedUpdatedTask);
	}

	@Override
	public void deleteTask(String taskId) {
		Task existingTask = findByIdOrElseThrow(taskId);
		taskRepository.delete(existingTask);
	}

	private Task findByIdOrElseThrow(String taskId) {
		return taskRepository
				.findById(taskId)
				.orElseThrow(
						() ->
								new ResourceNotFoundException(
										String.format(ExceptionMessage.TASK_NOT_FOUND_BY_ID.getMessage(), taskId)));
	}
}
