package com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link TutoringSession} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations and
 * includes additional query methods specific to tutoring sessions.
 */
@Repository
public interface TutoringSessionRepository extends JpaRepository<TutoringSession, Long> {

    /**
     * Find tutoring sessions associated with a specific tutor and course.
     *
     * @param tutorId The tutor ID.
     * @param courseId The course ID.
     * @return A list of tutoring sessions associated with the provided tutor and course.
     */
    List<TutoringSession> findByTutorIdAndCourseId(Long tutorId, Long courseId);

    /**
     * Finds tutoring sessions associated with a specific cycle.
     *
     * @param cycle The cycle number to filter by.
     * @return A list of tutoring sessions belonging to the specified cycle.
     */
    List<TutoringSession> findByCycle(int cycle);
}
