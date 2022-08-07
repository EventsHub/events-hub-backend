package com.saxakiil.eventshubbackend.transformer;

import com.saxakiil.eventshubbackend.dto.auth.ProfileRequest;
import com.saxakiil.eventshubbackend.model.AccountProfile;
import org.springframework.stereotype.Component;

@Component
public class AccountProfileTransformer {

    public AccountProfile transform(final Long id, final ProfileRequest profileRequest) {
        return AccountProfile.builder()
                .id(id)
                .phoneNumber(profileRequest.getPhoneNumber())
                .firstName(profileRequest.getFirstName())
                .lastName(profileRequest.getLastName())
                .birthday(profileRequest.getBirthday())
                .urlOnSite(profileRequest.getUrlOnSite())
                .address(profileRequest.getAddress())
                .build();
    }
}
