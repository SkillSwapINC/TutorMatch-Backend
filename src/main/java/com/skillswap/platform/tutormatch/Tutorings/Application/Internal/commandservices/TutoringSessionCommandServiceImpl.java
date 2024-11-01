package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionCommandService;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.CourseRepository;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.TutoringSessionRepository;
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

    /**
     * Constructs a new TutoringSessionCommandServiceImpl with the specified repositories.
     *
     * @param tutoringSessionRepository the repository used to manage tutoring session entities
     * @param courseRepository          the repository used to manage course entities
     */
    public TutoringSessionCommandServiceImpl(TutoringSessionRepository tutoringSessionRepository,
                                             CourseRepository courseRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Handles the creation of a new tutoring session based on the provided command.
     * Validates that the course exists and that the session title matches the course name.
     *
     * @param command the command containing the details for the new tutoring session
     * @return an Optional containing the created TutoringSession, or empty if creation fails
     * @throws IllegalArgumentException if the specified course ID does not exist or if the title
     * does not match the specified course name
     */
    @Override
    public Optional<TutoringSession> handle(CreateTutoringSessionCommand command) {

        Course course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid courseId: Course does not exist."));

        if (!command.title().contains(course.getName())) {
            throw new IllegalArgumentException("Title does not match the specified course.");
        }

        List<TutoringSession> existingSessions = tutoringSessionRepository.findByCourseId(command.courseId());

        TutoringSession session = new TutoringSession(command);
        tutoringSessionRepository.save(session);

        return Optional.of(session);
    }
}
