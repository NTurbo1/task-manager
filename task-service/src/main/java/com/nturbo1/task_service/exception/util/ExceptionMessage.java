package com.nturbo1.task_service.exception.util;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    TASK_NOT_FOUND_BY_ID("User with id %s not found");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
