package com.saxakiil.eventshubbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cards")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url_image")
    private String urlImage;

    private String title;

    private String place;

    private String description;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "url_on_event")
    private String urlOnEvent;

    @Column(name = "is_published")
    @Builder.Default
    private Boolean published = Boolean.FALSE;
}
