package com.saxakiil.eventshubbackend.dto.auth;

import com.saxakiil.eventshubbackend.dto.profile.ProfileRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private ProfileRequest profileRequest;
}
