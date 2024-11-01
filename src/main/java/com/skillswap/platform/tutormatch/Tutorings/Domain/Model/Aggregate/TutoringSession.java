package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.DailySchedule;
import com.skillswap.platform.tutormatch.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a tutoring session, including details such as title, description,
 * price, times for availability, and association with tutor and course.
 */
@Getter
@Entity
public class TutoringSession extends AuditableAbstractAggregateRoot<TutoringSession> {

    private String title;
    private String description;
    private Double price;

    /**
     * List of available schedules (times) for this tutoring session.
     * Each {@link DailySchedule} instance represents available
     * hours for a specific day of the week.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tutoring_session_id")
    private List<DailySchedule> times;

    private String image;
    private Long tutorId;
    private Long courseId;

    protected TutoringSession() {}

    /**
     * Constructs a new TutoringSession instance based on the provided command.
     * Initializes all fields based on the command and sets up the
     * schedule for all days of the week.
     *
     * @param command The command containing the details to
     * create a new tutoring session.
     */
    public TutoringSession(CreateTutoringSessionCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.price = command.price();
        this.times = command.times();
        this.image = command.image();
        this.tutorId = command.tutorId();
        this.courseId = command.courseId();

        List<DailySchedule> defaultTimes = IntStream.range(0, 7)
                .mapToObj(day -> new DailySchedule(day, new ArrayList<>()))
                .collect(Collectors.toList());

        if (command.times() != null) {
            for (DailySchedule schedule : command.times()) {
                defaultTimes.set(schedule.getDayOfWeek(), schedule);
            }
        }

        this.times = defaultTimes;
    }

}
