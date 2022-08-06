package com.saxakiil.eventshubbackend.dto.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrganizeProfileResponse {

    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlOnSite;
}