package com.saxakiil.eventshubbackend.dto.error;

import lombok.Data;

@Data
public class FieldError {
    private final String field;
    private final String code;
}
