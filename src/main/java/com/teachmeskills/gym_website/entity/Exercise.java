package com.teachmeskills.gym_website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "Exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercise", nullable = false)
    private long id;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "min_number_approaches", nullable = false)
    private long minNumberApproaches;

    @Column(name = "min_number_repetitions", nullable = false)
    private long minNumberRepetitions;

    @Column(name = "recommendations", nullable = false)
    private String recommendations;

}
