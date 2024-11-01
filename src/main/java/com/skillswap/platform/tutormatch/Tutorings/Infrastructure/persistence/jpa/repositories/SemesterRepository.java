package com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Semester} entities.
 */
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
