package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

/**
 * Represents the daily schedule for available tutoring hours on a specific day of the week.
 * Each instance specifies a day and the corresponding hours when tutoring is available.
 */
@Entity
public class DailySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The day of the week for this schedule.
     * 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
     */
    @Setter
    @Getter
    private Integer dayOfWeek;

    /**
     * List of available hours on this day.
     * Each entry in the list represents a time slot, e.g., ["10-11", "11-12"].
     */
    @Setter
    @Getter
    @ElementCollection
    private List<String> availableHours;

    protected DailySchedule() {}

    /**
     * Constructs a new DailySchedule with the specified day of the week and available hours.
     *
     * @param dayOfWeek      The day of the week (0-6) for this schedule.
     * @param availableHours List of available time slots on this day.
     */
    public DailySchedule(Integer dayOfWeek, List<String> availableHours) {
        this.dayOfWeek = dayOfWeek;
        this.availableHours = availableHours;
    }

}
