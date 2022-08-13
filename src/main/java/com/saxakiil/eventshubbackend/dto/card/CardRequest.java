package com.saxakiil.eventshubbackend.dto.card;

import com.saxakiil.eventshubbackend.validator.StartDateConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class CardRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String place;
    private String description;
    @StartDateConstraint
    private Long startDate;
    private String urlOnEvent;
}
