package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.DeleteTutoringCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.UpdateTutoringCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionCommandService;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.CourseRepository;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.TutoringSessionRepository;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;
import com.skillswap.platform.tutormatch.Users.Infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the TutoringSessionCommandService for managing tutoring sessions.
 */
@Service
public class TutoringSessionCommandServiceImpl implements TutoringSessionCommandService {

    private final TutoringSessionRepository tutoringSessionRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a new TutoringSessionCommandServiceImpl with the specified repositories.
     *
     * @param tutoringSessionRepository the repository used to manage tutoring session entities
     * @param courseRepository          the repository used to manage course entities
     * @param userRepository            the repository used to manage user entities
     */
    public TutoringSessionCommandServiceImpl(TutoringSessionRepository tutoringSessionRepository,
                                             CourseRepository courseRepository,
                                             UserRepository userRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Handles the creation of a new tutoring session based on the provided command.
     * Validates that the course exists, the title matches the course name,
     * and the tutor has a teacher role.
     *
     * @param command the command containing the details for the new tutoring session
     * @return an Optional containing the created TutoringSession, or empty if creation fails
     * @throws IllegalArgumentException if the specified course does not exist, the title
     * does not match the specified course name, or the tutor does not have a teacher role
     */
    @Override
    public Optional<TutoringSession> handle(CreateTutoringSessionCommand command) {

        if (command.tutorId() == null) {
            throw new IllegalArgumentException("TutorId cannot be null.");
        }

        User tutor = userRepository.findByTutorId(command.tutorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tutorId: Tutor does not exist."));
        if (tutor.getRoleType() != RoleType.teacher) {
            throw new IllegalArgumentException("The tutor must have a teacher role.");
        }

        Course course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid courseId: Course does not exist."));

        if (command.courseName() == null || command.courseName().isEmpty()) {
            throw new IllegalArgumentException("Course name must not be empty.");
        }

        if (!course.getName().trim().equals(command.courseName().trim())) {
            throw new IllegalArgumentException("Course name does not match the courseId provided.");
        }

        List<TutoringSession> existingSessions = tutoringSessionRepository
                .findByTutorIdAndCourseId(command.tutorId(), command.courseId());

        if (!existingSessions.isEmpty()) {
            throw new IllegalArgumentException("The tutor has already created a tutoring session for this course.");
        }

        TutoringSession session = new TutoringSession(command,course);
        tutoringSessionRepository.save(session);

        return Optional.of(session);
    }

    /**
     * Handles the update of an existing tutoring session based on the provided command.
     * Validates that the tutoring session exists.
     *
     * @param command the command containing the details to update the tutoring session
     * @return an Optional containing the updated TutoringSession, or empty if update fails
     * @throws IllegalArgumentException if the specified tutoring session does not exist
     */

    @Override
    public Optional<TutoringSession> handle(UpdateTutoringCommand command) {

        TutoringSession session = tutoringSessionRepository.findById(command.tutoringSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tutoringSessionId: Tutoring session does not exist."));

        session.updateTutoringSessionAttributes(command);

        tutoringSessionRepository.save(session);

        return Optional.of(session);
    }

    /**
     * Handles the deletion of an existing tutoring session based on the provided command.
     * Validates that the tutoring session exists.
     *
     * @param command the command containing the tutoring session ID to delete
     *                Cannot be null or less than 1
     * @throws IllegalArgumentException if the specified tutoring session does not exist
     * or an error occurs while deleting the tutoring session
     * @see DeleteTutoringCommand
     */

    @Override
    public void handle(DeleteTutoringCommand command) {

        if(!tutoringSessionRepository.existsById(command.tutoringId())){
            throw new IllegalArgumentException("Invalid tutoringId: Tutoring session does not exist.");
        }
        try {
            tutoringSessionRepository.deleteById(command.tutoringId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting tutoring session: %s".formatted(e.getMessage()));
        }
    }

}

