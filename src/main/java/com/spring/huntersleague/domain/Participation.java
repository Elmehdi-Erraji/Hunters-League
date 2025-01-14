package com.spring.huntersleague.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participation{

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;


    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne
    private Competition competition;

    @JsonIgnore
    @OneToMany(mappedBy = "participation")
    private List<Hunt> hunts;

    private Double score;

}