package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents an academic semester in the system, containing a collection of
 * courses offered within that semester.
 * This entity captures the semester's name and maintains a list of
 * associated courses.
 */
@Getter
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * List of courses offered in this semester.
     * Mapped by the `semester` field in the {@link Course} entity.
     */
    @Setter
    @OneToMany(mappedBy = "semester")
    private List<Course> courses;

    /**
     * Constructs a new Semester with a name and a list of courses.
     *
     * @param name    The name of the semester.
     * @param courses The list of courses associated with this semester.
     */
    public Semester(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Semester() {}

}
