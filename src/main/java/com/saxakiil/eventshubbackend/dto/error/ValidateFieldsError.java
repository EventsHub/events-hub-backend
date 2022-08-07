package com.saxakiil.eventshubbackend.dto.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ValidateFieldsError {
    private final List<FieldError> errorsList;

}
