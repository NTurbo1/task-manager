package com.nturbo1.task_service.mapper;

import com.nturbo1.task_service.controller.request.TaskRequest;
import com.nturbo1.task_service.controller.response.TaskResponse;
import com.nturbo1.task_service.model.collection.Task;
import com.nturbo1.task_service.service.dto.TaskDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  TaskResponse toResponse(TaskDto dto);

  List<TaskResponse> toResponseList(List<TaskDto> dtoList);

  TaskDto toDto(Task model);

  List<TaskDto> toDtoList(List<Task> modelList);

  TaskDto toDto(TaskRequest request);

  Task toModel(TaskDto dto);

  @Mapping(source = "id", target = "id", ignore = true)
  Task updateFromDto(TaskDto dto, @MappingTarget Task model);
}
