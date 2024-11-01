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
     * Finds a list of tutoring sessions associated with a specific course.
     *
     * @param courseId The ID of the course to filter tutoring sessions.
     * @return A list of {@link TutoringSession} entities that are associated
     * with the specified course ID.
     */
    List<TutoringSession> findByCourseId(Long courseId);

}
