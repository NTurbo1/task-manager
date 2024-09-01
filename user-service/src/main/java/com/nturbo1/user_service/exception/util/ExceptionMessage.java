package com.nturbo1.user_service.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

    public static final String USER_NOT_FOUND_BY_ID = "User with id %s not found";
    public static final String USER_NOT_FOUND_BY_EMAIL = "User with email %s not found";
}
