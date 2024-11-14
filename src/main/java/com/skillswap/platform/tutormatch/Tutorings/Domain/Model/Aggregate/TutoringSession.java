package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.UpdateTutoringCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.DailySchedule;
import com.skillswap.platform.tutormatch.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a tutoring session, including details such as courseName, description,
 * price, times for availability, and association with tutor and course.
 */
@Getter
@Entity
public class TutoringSession extends AuditableAbstractAggregateRoot<TutoringSession> {

    @Column(name = "title")
    private String courseName;

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

    /**
     * The ID of the tutor associated with this tutoring session.
     * Only users with RoleType `teacher` should have associated tutoring sessions.
     */
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    /**
     * Many-to-One relationship with Course, each tutoring session
     * is associated with a specific course.
     */
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private int cycle;

    protected TutoringSession() {}

    /**
     * Constructs a new TutoringSession instance based on the provided command.
     * Initializes all fields based on the command and sets up the
     * schedule for all days of the week.
     *
     * @param command The command containing the details to
     * create a new tutoring session.
     */
    public TutoringSession(CreateTutoringSessionCommand command,Course course) {
        this.courseName = course.getName();
        this.description = command.description();
        this.price = command.price();
        this.times = command.times();
        this.image = command.image();
        this.tutorId = command.tutorId();
        this.course = course;
        this.cycle = course.getCycle();
        if (!this.courseName.equals(course.getName())) {
            throw new IllegalArgumentException("Course name does not match the courseId provided.");
        }

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

    public void updateTutoringSessionAttributes(UpdateTutoringCommand command) {
        this.description = command.description();
        this.price = command.price();
        this.image = command.image();
    }

}
