package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a course offered within a specific semester in the academic curriculum.
 * Contains details about the course, including name, description, cycle (semester),
 * and association with a particular semester.
 */
@Getter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    /**
     * The semester (cycle) this course is part of.
     */
    private int cycle;

    /**
     * The semester in which this course is offered.
     * Mapped to the {@link Semester} entity to reflect academic terms.
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    /**
     * Constructs a new course with specified details.
     *
     * @param name        The name of the course.
     * @param description The description of the course.
     * @param cycle       The academic cycle in which the course is offered.
     * @param semester    The semester in which this course is offered.
     */
    public Course(String name, String description, int cycle, Semester semester) {
        this.name = name;
        this.description = description;
        this.cycle = cycle;
        this.semester = semester;
    }

    public Course() {}


}
