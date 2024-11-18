package com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Course} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations and
 * includes additional query methods specific to courses.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds a specific course by its ID.
     *
     * @param id The ID of the course to find.
     * @return An Optional containing the found course, or an empty Optional if not found.
     */
    Optional<Course> findById(Long id);

    List<Course> findByCycle(int cycle);
}