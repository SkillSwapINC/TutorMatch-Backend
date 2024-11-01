package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Semester;
import org.springframework.transaction.annotation.Transactional;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for managing operations related to semesters and their associated courses.
 */
@Service
public class SemesterService {

    private final CourseRepository courseRepository;

    /**
     * Constructs a new SemesterService with the specified CourseRepository.
     *
     * @param courseRepository the repository used to manage course entities
     */
    public SemesterService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Assigns the provided courses to the specified semester. This method updates each course's
     * semester reference and saves the updated courses to the repository.
     *
     * @param semester the semester to which the courses will be assigned
     */
    @Transactional
    public void assignCoursesToSemester(Semester semester) {
        semester.getCourses().forEach(course -> course.setSemester(semester));
        courseRepository.saveAll(semester.getCourses());
    }
}
