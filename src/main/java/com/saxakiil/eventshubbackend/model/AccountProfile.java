package com.saxakiil.eventshubbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_profiles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phoneNumber")
        })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String birthday;
}
