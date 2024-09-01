package com.nturbo1.task_service.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

    public static final String TASK_NOT_FOUND_BY_ID = "User with id %s not found";
}
