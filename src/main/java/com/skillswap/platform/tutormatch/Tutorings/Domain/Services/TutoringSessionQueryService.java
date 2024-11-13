package com.skillswap.platform.tutormatch.Tutorings.Domain.Services;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringBySemesterId;

import java.util.List;

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
}
