package com.saxakiil.eventshubbackend.transformer;

import com.saxakiil.eventshubbackend.dto.card.CardRequest;
import com.saxakiil.eventshubbackend.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardTransformer {

    public Card transform(final CardRequest cardRequest, final String urlImage, final Long id) {
        return Card.builder()
                .id(id)
                .title(cardRequest.getTitle())
                .description(cardRequest.getDescription())
                .place(cardRequest.getPlace())
                .startDate(cardRequest.getStartDate())
                .urlOnEvent(cardRequest.getUrlOnEvent())
                .urlImage(urlImage)
                .build();
    }

    public Card transform(final CardRequest cardRequest, final String urlImage) {
        return transform(cardRequest, urlImage, null);
    }
}
