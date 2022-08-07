package com.saxakiil.eventshubbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
    private String startDate;

    @Column(name = "url_on_event")
    private String urlOnEvent;

    @Column(name = "is_published")
    @Builder.Default
    private Boolean published = Boolean.TRUE;
//
//    @Column(name = "user_organize_id")
//    private long userOrganizeId;
//
//    @JsonBackReference
//    @ManyToMany(mappedBy = "favoriteCards")
//    private Set<User> likedUsers;
}
