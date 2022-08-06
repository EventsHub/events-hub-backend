package com.saxakiil.eventshubbackend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
    private String phoneNumber;

    private String firstName;
    private String lastName;
    private String birthday;

    private String address;
    private String urlOnSite;

}
