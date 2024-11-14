package com.skillswap.platform.tutormatch.Tutorings.Domain.Services;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsByTutorId;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringById;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringBySemesterId;

import java.util.List;
import java.util.Optional;

/**
 * Defines a service for querying tutoring sessions.
 * <p>
 * Provides methods to retrieve tutoring sessions based on various criteria.
 */
public interface TutoringSessionQueryService {

    /**
     * Retrieves all available tutoring sessions.
     *
     * @param query The query object (unused in this implementation).
     * @return A list of all tutoring sessions.
     */
    List<TutoringSession> handle(GetAllTutoringsQuery query);

    /**
     * Retrieves tutoring sessions associated with a specific semester.
     *
     * @param query The query object containing the semester ID.
     * @return A list of tutoring sessions belonging to the specified semester.
     */
    List<TutoringSession> handle(GetTutoringBySemesterId query);

    /**
     * Retrieves all tutoring sessions conducted by a specific tutor.
     *
     * @param query The query object containing the tutor's ID.
     * @return A list of tutoring sessions associated with the specified tutor.
     */
    List<TutoringSession> handle(GetAllTutoringsByTutorId query);

    /**
     * Retrieves a specific tutoring session by its ID.
     *
     * @param query The query object containing the tutoring session's ID.
     * @return An Optional containing the tutoring session if found, or an empty Optional otherwise.
     */
    Optional<TutoringSession> handle(GetTutoringById query);
}
