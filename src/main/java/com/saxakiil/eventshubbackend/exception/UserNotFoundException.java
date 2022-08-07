package com.saxakiil.eventshubbackend.exception;

import lombok.extern.slf4j.Slf4j;

import static com.saxakiil.eventshubbackend.util.Constants.USER_IS_NOT_FOUND_EXCEPTION;

@Slf4j
public class UserNotFoundException extends Exception {

    public UserNotFoundException(Long id) {
        log.error(String.format(USER_IS_NOT_FOUND_EXCEPTION, id));
    }
}