package com.saxakiil.eventshubbackend.exception;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import static com.saxakiil.eventshubbackend.util.Utils.CARD_IS_NULLABLE_EXCEPTION;

@Slf4j
public class NullableCardException extends Exception {

    public NullableCardException() {
        log.error(String.format(CARD_IS_NULLABLE_EXCEPTION, Instant.now().toString()));
    }
}
