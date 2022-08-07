package com.saxakiil.eventshubbackend.dto.card;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Data
public class CardRequest {

    @NotNull
    private String title;
    @NotNull
    private String place;
    private String description;
    @NotNull
    private String startDate;
    private String urlOnEvent;
    private MultipartFile imageFile;
}
