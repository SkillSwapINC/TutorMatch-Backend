package com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Course} entities.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
