package com.saxakiil.eventshubbackend.dto.profile;

import com.saxakiil.eventshubbackend.validator.BirthdayFormatConstraint;
import com.saxakiil.eventshubbackend.validator.PhoneNumberConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class ProfileRequest {

    @PhoneNumberConstraint
    private String phoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @BirthdayFormatConstraint
    private String birthday;
}
